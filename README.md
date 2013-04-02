# twfw

A Clojure project creating a gexf file of ones twitter followers.

## Usage

```
lein run USERNAME outfile.gexf
```

Where USERNAME is the username to get the 1st level follower graph from,
outfile is the file the gexf info should be written.

Due to twitter api rate limiting this takes forever (especially the name
resolution part) - memoizing helps, however you might run into memory
issues.

## License

Copyright © 2013 Michael Bauer 

Distributed under the Eclipse Public License, the same as Clojure.
