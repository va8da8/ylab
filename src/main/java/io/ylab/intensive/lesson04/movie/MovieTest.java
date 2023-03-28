package io.ylab.intensive.lesson04.movie;


import io.ylab.intensive.lesson04.DbUtil;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;


public class MovieTest {


    public static void main(String[] args) throws SQLException, IOException {

        DataSource dataSource = initDb();
        MovieLoader movieLoader = new MovieLoaderImpl(dataSource);


        File dataFile = new File
                ("src/main/java/io/ylab/intensive/" +
                        "lesson04/movie/film.csv");
        movieLoader.loadData(dataFile);

        /*
          Тут написать в комментариях запрос получения всех
          SELECT subject, COUNT(*) as count
          FROM movies
          GROUP BY subject;
         */
    }


    private static DataSource initDb() throws SQLException {

        String createMovieTable = "drop table if exists movie;"
                + "CREATE TABLE IF NOT EXISTS movie (\n"
                + "\tid bigserial NOT NULL,\n"
                + "\t\"year\" int4,\n"
                + "\tlength int4,\n"
                + "\ttitle varchar,\n"
                + "\tsubject varchar,\n"
                + "\tactors varchar,\n"
                + "\tactress varchar,\n"
                + "\tdirector varchar,\n"
                + "\tpopularity int4,\n"
                + "\tawards bool,\n"
                + "\tCONSTRAINT movie_pkey PRIMARY KEY (id)\n"
                + ");";
        DataSource dataSource = DbUtil.buildDataSource();
        DbUtil.applyDdl(createMovieTable, dataSource);
        return dataSource;
    }
}