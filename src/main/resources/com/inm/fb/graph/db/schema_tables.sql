----------------------------------------------------------------------
-- Query to create table for meta data for the facebook post
----------------------------------------------------------------------
CREATE TABLE meta_data (
   post_id      VARCHAR(50) PRIMARY KEY NOT NULL,
   created_date TIMESTAMP NOT NULL,
   post_type    VARCHAR(50) NOT NULL,
   content      TEXT,
   link         VARCHAR(50),
   db_created_date TIMESTAMP,
   updated_date TIMESTAMP
);
CREATE INDEX post_id_idx ON meta_data (post_id);
CREATE INDEX post_db_created_idx ON meta_data (db_created_date);
CREATE INDEX post_id_created_idx ON meta_data (post_id, created_date);
CREATE INDEX post_type_created_idx ON meta_data (created_date, post_type);

----------------------------------------------------------------------
-- Query to create table to contain performance metrics for the post
----------------------------------------------------------------------
CREATE TABLE perf_metrics (
   post_id     VARCHAR(50),
   likes       BIGINT,
   shares      BIGINT,
   comments    BIGINT,
   reaches     BIGINT,
   impressions BIGINT,
   engagements BIGINT,
   engagers    BIGINT,
   created_date TIMESTAMP,
   updated_date TIMESTAMP
);
CREATE INDEX post_id_perf_metrics_idx ON perf_metrics (post_id);
CREATE INDEX post_updated_perf_metrics_idx ON perf_metrics (updated_date);
CREATE INDEX post_created_perf_metrics_idx ON perf_metrics (created_date);
CREATE INDEX post_id_updated_perf_metrics_idx ON perf_metrics (post_id, updated_date);