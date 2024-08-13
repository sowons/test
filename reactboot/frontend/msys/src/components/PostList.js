// src/components/PostList.js

import axios from 'axios';
import React, { useEffect, useState } from 'react';

const PostList = () => {
  const [posts, setPosts] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [page, setPage] = useState(1);
  const [size, setSize] = useState(10);

  useEffect(() => {
    const fetchPosts = async () => {
      try {
        const response = await axios.get(`http://localhost:8080/posts?page=${page}&size=${size}`);
        setPosts(response.data);
      } catch (err) {
        setError(err.message);
      } finally {
        setLoading(false);
      }
    };

    fetchPosts();
  }, [page, size]);

  if (loading) return <div>Loading...</div>;
  if (error) return <div>Error: {error}</div>;

  return (
    <div>
      <h1>게시글 목록</h1>
      <ul>
        {posts.map(post => (
          <li key={post.id}>
            <h2>{post.potitle}</h2>
            <p>{post.pocontent}</p>
            <p>조회수: {post.viewCnt} / 댓글수: {post.replyCnt}</p>
            <p>작성일: {new Date(post.podate).toLocaleDateString()}</p>
          </li>
        ))}
      </ul>
      <button onClick={() => setPage(prev => Math.max(prev - 1, 1))}>이전 페이지</button>
      <button onClick={() => setPage(prev => prev + 1)}>다음 페이지</button>
    </div>
  );
};

export default PostList;