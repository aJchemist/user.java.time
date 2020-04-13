(ns user.java.time.script.print-chrono-version-test
  (:require
   [clojure.test :as test :refer [deftest is are testing]]
   [user.java.time.script.print-chrono-version :refer :all]
   )
  (:import
   java.time.ZoneId
   ))


(def ^ZoneId tz-asia-seoul (ZoneId/of "Asia/Seoul"))


(deftest main
  (is
    (not=
      (chrono-version-str)
      (chrono-version-str nil tz-asia-seoul))))
