(ns maze_jam.client
  (:require [clj-http.client :as client]
            [cheshire.core :refer :all]))

(def endpoint "http://mazesjam.apphb.com/mazes/render")#'maze_jam.client/endpoint

(def maze [[2 14 10]
           [5 9 11]
           [3 15 9]])


(def maze2 [[6,8,10,10,14,8,12,14,12,10],[7,9,11,15,11,11,15,11,13,11],[7,9,13,11,13,13,9,9,15,9],[7,9,13,15,9,15,15,11,13,11],[5,11,15,13,9,9,9,11,15,9],[3,15,11,15,9,15,11,11,11,11],[5,11,13,13,13,9,13,9,13,9],[5,13,11,13,11,11,11,13,13,11],[3,15,13,11,11,11,11,11,11,11],[5,13,9,13,13,13,13,13,13,9]])

(generate-string {:maze maze})                  ;"{\"maze\":[[2,14,10],[5,9,11],[3,15,9]]}"

(generate-string maze)                  ;"[[2,14,10],[5,9,11],[3,15,9]]"





(client/post endpoint {:form-params {:maze "[[1,1,1],[2,2,2]]"  ;(generate-string maze2)
                                     :cellSize 30}
                       ;; :content-type :json
                       })
