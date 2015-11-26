(ns app.core
  (:require
    [clojure.string :as str]
    [rum]
    [cljsjs.react-bootstrap]
    [reagent.core :as reagent :refer [atom]]
    [inkspot.color :as color]
    [inkspot.color-chart :as cc]
  )
)

(enable-console-print!)

(defn el [id] (js/document.getElementById id))
(defn del [id]
	(let [c (.. js/document (createElement "DIV"))
		  d (.. js/document (getElementById id) (appendChild c))]
		d
	)
)
(defn cel [content]
	(let [c (.. js/document (createElement "DIV"))]
		(aset c "innerHTML" content)
		c
	)
)

(def pyes (js/React.createElement "p" #js{} "Yes!"))

(def yes (js/React.createElement "div" #js{} pyes))


(def button (.createFactory js/React js/ReactBootstrap.Button))
(def mybutton (button #js{:id "buttonClickMe"} "Click Me"))

(defn divbutton [button] (js/React.createElement "div" #js{} button))
(defn divbutton2 [] (js/React.createElement "div" #js{} mybutton))


(defn main []
  ; (let [c (.. js/document (createElement "DIV"))]
  ;   (aset c "innerHTML" "<p>i'm dynamically created</p>")
  ;   (.. js/document (getElementById "container") (appendChild c))
  ; )
  ;(.render js/React yes (el "bootit"))
  (.render js/React mybutton (el "bootit"))
)

(def testing "YES!")
(defn tfunc [] "Ph YES!")
(defn p [text] (.log js/console text))

; (rum/defc label [n text]
;   [:.label (repeat n text)])

; (rum/mount (label 5 "abc") (.. js/document (getElementById "bootit")))



; (.. (el "bootit") (appendChild (cel "<p>Yeah!</p>")) )

(def buttonFactory (.createFactory js/React js/ReactBootstrap.Button))
(def Button (reagent/adapt-react-class buttonFactory))

(defn home-page []
  [:div [:h2 "Welcome to reagent-test"]
   [:div [:a {:href "#/about"} "go to about page"]]
   [:div
    [:p
      (reagent/create-element "span" #js{} "Hello ")
      (reagent/create-element "span" #js{} "P")
      (reagent/adapt-react-class (button #js{:id "buttonClickMe"} "Click Me!!"))
      (button #js{:id "buttonClickMe"} "Click Me 2!!")
    ]
   ]
  ])


(reagent/render [home-page] (el "app"))

(reagent/create-element "div"
                     #js{:className "foo"}
                     "Hello "
                     (reagent/create-element "strong"
                                        #js{}
                                        "world"))

(reagent/create-element "div"
                     #js{:className "foo"}
                     "Hello "
                     (reagent/create-element "strong"
                                        #js{}
                                        "world"))


(reagent/render [home-page] (el "app"))
