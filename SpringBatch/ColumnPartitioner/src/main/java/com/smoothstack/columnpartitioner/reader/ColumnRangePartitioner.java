package com.smoothstack.columnpartitioner.reader;

import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public class ColumnRangePartitioner implements Partitioner {
    private JdbcOperations JdbcTemplate;
    private String table;
    private String column;

    public JdbcOperations getJdbcTemplate() {
        return JdbcTemplate;
    }

    public void setJdbcTemplate(JdbcOperations jdbcTemplate) {
        JdbcTemplate = jdbcTemplate;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public void setDataSource( DataSource dataSource) {
        this.JdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public Map<String, ExecutionContext> partition(int gridSize) {
        int min = this.getJdbcTemplate().queryForObject("select min( " +column+ ") from " + table , Integer.class);
        int max = this.getJdbcTemplate().queryForObject("select max( " +column+ ") from " + table , Integer.class);

        int targetSize = (max - min ) /gridSize +1;

        Map<String, ExecutionContext> result = new HashMap<>();
        int number =0 ;
        int start  = min;
        int end = start + targetSize -1;
        while (start <=max){
            ExecutionContext value = new ExecutionContext();
            result.put("partition"+number, value);

            if (end >= max ){
                end = max;
            }

            value.putInt("minValue", start);
            value.putInt("maxValue", end);

            start += targetSize;
            end += targetSize;

            number++;



        }

        return result;
    }
}
