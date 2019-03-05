(ns ^:figwheel-no-load computus.dev
  (:require
    [computus.core :as core]
    [devtools.core :as devtools]))


(enable-console-print!)

(devtools/install!)

(core/init!)
