(ns maze-jam.core)

(defn make-maze-template [width height]
  (vec (for [x (range width)]
    (vec (for [y (range height)]
      -1))))
  )

(defn possible-neighbours [x y]
  (distinct (for [delta-x [-1 0 1]
                  delta-y [-1 0 1]]
              [(+ delta-x x) (+ delta-y y)])))

;todo remove values that contain negative coordinates
(defn neighbours [x y]
  (for [[x y] (possible-neighbours x y)
        :when (and (<= 0 x) (<= 0 y))]
    [x y])
  )

(defn unvisited-neighbours [x y maze]
  (for [neighbour (neighbours x y)
    :when (> 0 (y (x maze)))]
    [x y])
  )
