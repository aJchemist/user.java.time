(ns user.java.time.script.print-chrono-version
  (:require
   [clojure.tools.cli :as cli]
   )
  (:import
   java.time.ZonedDateTime
   java.time.ZoneId
   java.time.format.DateTimeFormatter
   ))


(def dt-fmt-str "yyyy.D")


(def ^ZoneId tz-utc (ZoneId/of "UTC"))


(defn zdt-second-of-day
  [^ZonedDateTime zdt]
  (.. zdt (toLocalTime) (toSecondOfDay)))


(defn chrono-version-str
  ([]
   (chrono-version-str nil))
  ([fmt]
   (chrono-version-str fmt nil))
  ([fmt tz]
   (let [fmt                (or fmt dt-fmt-str)
         tz                 (or tz tz-utc)
         ^ZonedDateTime zdt (ZonedDateTime/now tz)]
     (str (.. (DateTimeFormatter/ofPattern fmt) (format zdt))
          "." (zdt-second-of-day zdt)))))



(def cli-options
  [[nil "--format FORMAT" "DateTimeFormatter"]
   [nil "--time-zone TIMEZONE" "Time zone id"
    :parse-fn #(ZoneId/of %)]])


(defn -main
  [& xs]
  (let [{:keys [format time-zone]} (cli/parse-opts xs cli-options)]
    (println (chrono-version-str format time-zone))))
