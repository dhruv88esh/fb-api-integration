---------------------------------------------------------
-- Query to get post which were posted on particular day
----------------------------------------------------------
SELECT * FROM metadata WHERE TO_CHAR(created_date, 'YYYY-MM-DD') = '2017-05-12';


----------------------------------------------------------------------
-- Query to get post which were posted on particular month and year
----------------------------------------------------------------------
SELECT * FROM metadata WHERE EXTRACT(MONTH FROM TIMESTAMP created_date) = 5 AND EXTRACT(YEAR FROM TIMESTAMP created_date) = 2017;


----------------------------------------------------------------------
-- Query to get post which were posted one week before
----------------------------------------------------------------------
SELECT * FROM metadata WHERE EXTRACT(WEEK FROM TIMESTAMP created_date) > 1;



