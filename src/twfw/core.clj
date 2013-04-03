(ns twfw.core
  (:gen-class)
  (:require [gexf.core :as gexf]
            [clj-http.client :as http]
            [clojure.data.json :as json]))

(def api-url "https://api.twitter.com/1/")

(defn make-param
  [x]
  (let [[k v] x
        k (subs (str k) 1)]
    (str k "=" v)))

(defn make-params
    "make parameters from a map"
    [kvs]
    (clojure.string/join "&" (map make-param kvs)))

 
(defn tw-get 
  "get a url from twitter api v1 and return a map"
  [url kv]
  (Thread/sleep 25000)
  (try
    (let [url (str api-url url ".json?" (make-params kv))]
      (json/read-str (:body (http/get url))))
    (catch Exception e nil)))
    

(defn get-screen-name-raw
  "returns the screen name for an id"
  [id]
  (get (first (tw-get "users/lookup" {:user_id id}))
       "screen_name"))

(def get-screen-name (memoize get-screen-name-raw))

(defn get-followers-raw
  "return the followers of a screen name"
  [screen-name]
  (map get-screen-name
       (get (tw-get "followers/ids" 
                    {:screen_name screen-name}) 
            "ids")))

(def get-followers (memoize get-followers-raw))

(defn create-edges
  "create the edges"
  [screenname]
  (for [x (get-followers screenname)] [x screenname]))

(defn -main [& args]
  (let [username (first args)
        filename (last args)]
    (println (format "Getting follower graph for %s" username))
    (spit filename 
          (gexf/write
           (into []
                 (reduce concat 
                         (map create-edges 
                              (get-followers username)))))))) 
    
  