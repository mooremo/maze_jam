(ns maze-jam.client
  (:require [clj-http.client :as client]
            [cheshire.core :refer :all]))

(defn maze->png
  [maze]
  (client/post "http://mazesjam.apphb.com/mazes/render" {:form-params {:maze (generate-string maze)
                                                                       :cellSize 30}
                                                         :as :byte-array}))

(defn write-file [response]
     (with-open [w (clojure.java.io/output-stream "/tmp/maze.png")]
            (.write w (:body response))))
