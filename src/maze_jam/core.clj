(ns maze-jam.core)

(defn make-maze-template [width height]
  (vec (for [x (range height)]
    (vec (for [y (range width)]
      0)))))

(defn possible-neighbors
  [x y]
  [[x (dec y)] [x (inc y)] [(dec x) y] [(inc x) y]])

(defn neighbors [x y maze]
  (let [height (count maze)
        width (count (first maze))]
    (for [[x y] (possible-neighbors x y)
          :when (and (<= 0 x) (<= 0 y) (< x width) (< y height))]
      [x y])))

(def maze (make-maze-template 3 5))     ;

(defn unvisited-neighbors
  [[x y] maze]
  (for [neighbor (neighbors x y maze)
    :when (= 0 ((maze y) x))]
    neighbor))

(def mask
  {:north 1
   :south 2
   :east 4
   :west 8})

(defn bore
  [[x1 y1] [x2 y2]]
  (-> (cond (< x1 x2) :east
         (< x2 x1) :west
         (< y1 y2) :south
         (< y2 y1) :north)
  mask))

(defn carve
   [[x1 y1] [x2 y2] maze]
   (-> maze
       (update-in [y1 x1] + (bore [x1 y1] [x2 y2]))
       (update-in [y2 x2] + (bore [x2 y2] [x1 y1]))))

(defn step
  [maze visited-cells]
  (if (empty? visited-cells)
    maze
    (let [current (first visited-cells)
          unvisited (unvisited-neighbors current maze)]
      (if (empty? unvisited)
        (recur maze (rest visited-cells))
        (let [next-cell (rand-nth unvisited)
              new-maze (carve current next-cell maze)
              new-visited (conj visited-cells (rand-nth unvisited))]
          (recur new-maze new-visited))))))

(defn gen-maze
  [w h]
  (step (make-maze-template w h) '([0 0])))

(comment
  (gen-maze 5 5)
)
