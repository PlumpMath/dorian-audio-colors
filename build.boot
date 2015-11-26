(set-env!
  :source-paths   #{"src"}
  :resource-paths #{"html"}
  :dependencies '[
    [adzerk/boot-cljs          "0.0-3308-0"      :scope "test"]
    [adzerk/boot-cljs-repl     "0.1.10-SNAPSHOT" :scope "test"]
    [adzerk/boot-reload        "0.3.1"           :scope "test"]
    [pandeiro/boot-http        "0.6.3-SNAPSHOT"  :scope "test"]
    [deraen/boot-less          "0.4.0"           :scope "test"]
    [org.clojure/clojure       "1.7.0"]
    [org.clojure/clojurescript "1.7.48"]
    [cljsjs/react              "0.13.3-0"]
    [reagent "0.5.0" :exclusions [cljsjs/react]]
    ;[rum                       "0.2.7" :exclusions [cljsjs/react]]
    ;[sablono                   "0.3.4"]
    [cljsjs/react-bootstrap    "0.21.2-0" :exclusions [cljsjs/react]]
    [datascript "0.11.5"]
    ; [org.clojure/tools.analyzer "0.6.6"]
    ; [org.clojure/core.memoize "0.5.6"]
    ; [org.clojure/tools.reader "0.9.2"]
    ; [org.ow2.asm/asm-all "4.2"]
    ; [org.clojure/tools.analyzer.jvm "0.6.7"]
    ; [org.clojure/tools.analyzer.js "0.1.0-beta5"]
    [rm-hull/inkspot "0.0.1-SNAPSHOT"]
    [rm-hull/monet "0.2.1"]
    [org.clojure/core.async "0.1.346.0-17112a-alpha"]
    ;[cljs-ratios "0.2.1"]
    [com.gfredericks/cljs-numbers "0.1.2"]
    [com.kaicode/infamous "0.7.8-SNAPSHOT"]
  ]
)

;(task-options! repl {:init-ns 'app.core})

(require
  '[adzerk.boot-cljs      :refer [cljs]]
  '[adzerk.boot-cljs-repl :refer [cljs-repl start-repl]]
  ;'[adzerk.boot-reload    :refer [reload]]
  '[adzerk.boot-reload :refer :all]
  '[pandeiro.boot-http    :refer [serve]]
  '[deraen.boot-less :refer :all]
  ; '[rum :refer :all]
)

(deftask dev1 []
  (set-env! :source-paths #{"src" "test"})
  (comp (serve :dir "target/")
        (watch)
        (speak)
        (reload :on-jsload 'app.core/main)
        (cljs-repl)
        (cljs :source-map true :optimizations :none)
  )
)

(deftask dev
  "Dev build task."
  []
  (set-env! :source-paths #{"src" "test"})
  (comp
      (serve :dir "target/")
      (watch)
      (speak)
      (reload :on-jsload 'app.core/main)
      (cljs-repl)
      (cljs :source-map true
            :optimizations :none
            :compiler-options {
                :warnings {:single-segment-namespace false}
            }
      )
      (less)
  )
)

(deftask build []
  (set-env! :source-paths #{"src"})
  (comp (cljs :optimizations :advanced)))
