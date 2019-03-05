(ns computus.core
  (:require
   [reagent.core :as r]))

;; ------------------------
;; Functions

(defn a [year] (mod year 19))
(defn b [year] (quot year 100))
(defn c [year] (mod year 100))
(defn d [year] (quot (b year) 4))
(defn e [year] (mod (b year) 4))
(defn f [year] (quot (+ (b year) 8) 25))
(defn g [year] (quot (+ (- (b year) (f year)) 1) 3))
(defn h [year] (mod (+ 15 (- (- (+ (* 19 (a year)) (b year)) (d year)) (g year))) 30))
(defn i [year] (quot (c year) 4))
(defn k [year] (mod (c year) 4))
(defn l [year] (mod (- (- (+ 32 (+ (* 2 (e year)) (* 2 (i year)))) (h year)) (k year)) 7))
(defn m [year] (quot (+ (+ (a year) (* 11 (h year))) (* 22 (l year))) 451))
(defn month [year] (quot (+ (- (+ (h year) (l year)) (* 7 (m year))) 114) 31))
(defn day [year] (+ 1 (mod (+ 114 (-  (+ (h year) (l year)) (* 7 (m year)))) 31)))

;; -------------------------
;; Views

(defn year-input [value]
  [:input {:type "text"
           :value @value
           :on-change #(reset! value (-> % .-target .-value))}])

(defn calc []
  (let [val (r/atom "2019")]
    (fn []
      [:div#main
       [:h1 "Input Year: " [year-input val]]      
       [:div       
        [:h2 "Month: " (month (js/parseInt @val))]
        [:h2 "Day: " (day (js/parseInt @val))]]
       ])))

(defn home-page []
  [:div [:h2 "year"]])

;; -------------------------
;; Initialize app

(defn mount-root []
  (r/render [calc] (.getElementById js/document "app")))

(defn init! []
  (mount-root))
