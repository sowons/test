// src/components/PostList.js

import axios from 'axios';
import React, { useEffect, useState } from 'react';

const PostList = () => {
  const [posts, setPosts] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [page, setPage] = useState(1);
  const [size, setSize] = useState(10);
  const [totalPages, setTotalPages] = useState(0); // 총 페이지 수를 저장할 상태 변수

  useEffect(() => {
    const fetchPosts = async () => {
      try {
        const response = await axios.get(`http://localhost:8080/list/posts?page=${page}&size=${size}`);
        setPosts(response.data.content); // 게시글 데이터 설정
        setTotalPages(response.data.totalPages); // 총 페이지 수 설정
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

  // 페이지 버튼 생성
  const renderPageButtons = () => {
    const buttons = [];
    for (let i = 1; i <= totalPages; i++) {
      buttons.push(
        <button
          key={i}
          onClick={() => setPage(i)}
          style={{ margin: '0 5px', backgroundColor: page === i ? '#007bff' : '#ffffff' }} // 현재 페이지 버튼 강조
        >
          {i}
        </button>
      );
    }
    return buttons;
  };

  return (
    <div>
      <h1>게시글 목록</h1>
      <ul>
        {posts.map(post => (
          <li key={post.id}>
            <h2>{post.poTitle}</h2>
            <p>{post.poContents}</p>
            <p>조회수: {post.viewcnt} / 댓글수: {post.replycnt}</p>
            <p>작성일: {new Date(post.poDate).toLocaleDateString()}</p>
          </li>
        ))}
      </ul>
      <div>
        {renderPageButtons()} {/* 페이지 버튼 렌더링 */}
      </div>
      <div>
        <button onClick={() => setPage(prev => Math.max(prev - 1, 1))} disabled={page === 1}>이전 페이지</button>
        <button onClick={() => setPage(prev => Math.min(prev + 1, totalPages))} disabled={page === totalPages}>다음 페이지</button>
      </div>
    </div>
  );
};

export default PostList;