-- количество фильмов каждого жанра
SELECT subject, COUNT(*) as count
FROM movie
GROUP BY subject;