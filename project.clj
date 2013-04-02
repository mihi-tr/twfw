(defproject twfw "0.1.0-SNAPSHOT"
  :description "get a network of twitter followers (v1 api)"
  :url "http://github.com/mihi-tr/twfw"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.4.0"]
                  [clj-http "0.6.5"]
                  [org.clojure/data.json "0.2.1"]
                  [gexf "0.1.0-SNAPSHOT"]]
  :main twfw.core
  :disable-implicit-clean true
  )
