package com.msys.reactboot.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.msys.reactboot.domain.Post;
import com.msys.reactboot.dto.Page;

@Repository
public class PostDao {
private final JdbcTemplate jdbcTemplate;

    public PostDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Post> getPosts(Page page) {
        int startIndex = page.getIndex() + 1; // ROW_NUMBER()는 1부터 시작
        int endIndex = page.getIndex() + page.getRows();

        String sql = "SELECT * FROM ( " +
                     "  SELECT POST.*, " +
                     "         ROW_NUMBER() OVER (ORDER BY ponum) AS RNUM " +
                     "  FROM POST " +
                     ") WHERE RNUM BETWEEN ? AND ?";

        return jdbcTemplate.query(sql, ps -> {
            ps.setInt(1, startIndex);
            ps.setInt(2, endIndex);
        }, postRowMapper());
    }

    private RowMapper<Post> postRowMapper() {
        return (rs, rowNum) -> {
            Post post = new Post();
            post.setId(rs.getString("id"));
            post.setPotitle(rs.getString("potitle"));
            post.setPocontent(rs.getString("pocontent"));
            post.setPowarning(rs.getBoolean("powarning"));
            post.setViewCnt(rs.getInt("viewCnt"));
            post.setReplyCnt(rs.getInt("replyCnt"));
            post.setPodate(rs.getDate("podate"));
            post.setPofile(rs.getBytes("pofile"));
            return post;
        };
    }
}
