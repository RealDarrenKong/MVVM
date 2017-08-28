package com.darrenkong.mvvm.feature.data;

import java.util.List;

/**
 * Created by darrenkong on 27/8/17.
 */

public class EarthQuakeListResponse {

    public final String type;
    public Metadata metadata;
    public List<Feature> features;
    public List<Double> bbox;

    public EarthQuakeListResponse(String type, Metadata metadata, List<Feature> features, List<Double> bbox) {
        this.type = type;
        this.metadata = metadata;
        this.features = features;
        this.bbox = bbox;
    }

    public class Metadata {

        private final String generated;
        private final String url;
        private final String title;
        private final Integer status;
        private final String api;
        private final Integer count;

        public Metadata(String generated, String url, String title, Integer status, String api, Integer count) {
            this.generated = generated;
            this.url = url;
            this.title = title;
            this.status = status;
            this.api = api;
            this.count = count;
        }
    }

    public class Feature {

        public final String type;
        public final Properties properties;
        public final Geometry geometry;
        public final String id;

        public Feature(String type, Properties properties, Geometry geometry, String id) {
            this.type = type;
            this.properties = properties;
            this.geometry = geometry;
            this.id = id;
        }

        public class Properties {

            public final Double mag;
            public final String place;
            public final String time;
            public final String url;
            public final String detail;
            public final String alert;
            public final String status;
            public final String tsunami;
            public final String code;
            public final String ids;
            public final String type;
            public final String title;

            public Properties(Double mag, String place, String time, String url, String detail, String alert, String status, String tsunami, String code,
                              String ids, String type, String title) {
                this.mag = mag;
                this.place = place;
                this.time = time;
                this.url = url;
                this.detail = detail;
                this.alert = alert;
                this.status = status;
                this.tsunami = tsunami;
                this.code = code;
                this.ids = ids;
                this.type = type;
                this.title = title;
            }
        }

        public class Geometry {

            private final String type;
            private final List<Double> coordinates;

            public Geometry(String type, List<Double> coordinates) {
                this.type = type;
                this.coordinates = coordinates;
            }
        }
    }
}
