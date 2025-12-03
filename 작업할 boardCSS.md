/* ===========================
   Global
=========================== */
body {
    font-family: "Pretendard", sans-serif;
    background: #f6f7f9;
    margin: 0;
    padding: 0;
}

.page-title {
    font-size: 28px;
    font-weight: 700;
    margin-bottom: 24px;
}

/* 기본 입력 요소 */
.input-text {
    width: 100%;
    height: 44px;
    padding: 0 12px;
    border: 1px solid #ddd;
    border-radius: 10px;
    outline: none;
    transition: .2s;
}
.input-text:focus {
    border-color: #0A84FF;
}

.textarea-content {
    width: 100%;
    border: 1px solid #ddd;
    padding: 12px;
    border-radius: 10px;
    resize: none;
    transition: .2s;
}
.textarea-content:focus {
    border-color: #0A84FF;
}

.input-file {
    padding: 10px 4px;
}

/* ===========================
   Animation
=========================== */
.fade-up {
    animation: fadeUp .4s ease-out;
}

@keyframes fadeUp {
    from { opacity: 0; transform: translateY(10px); }
    to { opacity: 1; transform: translateY(0); }
}

/* ===========================
   Buttons (공용)
=========================== */

.btn-primary {
    background: #222;
    color: #fff;
    border: none;
    padding: 10px 22px;
    border-radius: 10px;
    cursor: pointer;
    transition: 0.2s;
}
.btn-primary:hover {
    background: #000;
    transform: scale(1.03);
}

.btn-secondary {
    background: #f2f2f2;
    border: 1px solid #ccc;
    padding: 10px 22px;
    border-radius: 10px;
    cursor: pointer;
}

.btn-small {
    padding: 6px 12px;
    border-radius: 8px;
    font-size: 13px;
    cursor: pointer;
    transition: .2s;
}

/* 버튼 세부 종류 */
.btn-reply {
    background: #e5f2ff;
    border: 1px solid #b5d8ff;
}
.btn-reply:hover {
    background: #d8eaff;
}

.btn-edit {
    background: #f1f1f1;
    border: 1px solid #ccc;
}
.btn-edit:hover {
    background: #e6e6e6;
}

.btn-delete {
    background: #ff5c5c;
    border: 1px solid #e04a4a;
    color: #fff;
}
.btn-delete:hover {
    background: #e44848;
}

.btn-delete-line {
    background: #fff;
    color: #ff5555;
    border: 1px solid #ff8888;
    padding: 10px 20px;
    border-radius: 10px;
}

.btn-area {
    margin-top: 10px;
    text-align: right;
    display: flex;
    gap: 10px;
    justify-content: flex-end;
}

/* ===========================
   게시판 목록
=========================== */
.board-section {
    max-width: 1280px;
    margin: 0 auto;
    padding: 80px 32px;
    animation: fadeUp .5s ease-out;
}

.board-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 28px;
}
.board-header h2 {
    font-size: 28px;
    font-weight: 700;
}

.write-btn {
    padding: 10px 20px;
    border-radius: 10px;
}

.search-box {
    display: flex;
    gap: 12px;
    margin-bottom: 32px;
}

.category-select,
.search-input {
    height: 44px;
    padding: 0 14px;
    border-radius: 10px;
    border: 1px solid #ddd;
    font-size: 14px;
}
.search-input { flex: 1; }
.search-btn { padding: 0 24px; border-radius: 10px; }

.board-table {
    background: rgba(255,255,255,0.35);
    backdrop-filter: blur(16px);
    border-radius: 16px;
    padding: 20px;
    border: 1px solid rgba(255,255,255,0.25);
}

.board-table table {
    width: 100%;
    border-collapse: collapse;
}

.board-table th {
    padding: 14px 0;
    font-weight: 600;
    border-bottom: 1px solid #ddd;
}
.board-table td {
    padding: 14px 0;
    border-bottom: 1px solid #eee;
    text-align: center;
    font-size: 14px;
}
.board-table td.title {
    text-align: left;
    padding-left: 10px;
}
.board-table tbody tr:hover {
    background: rgba(255,255,255,0.55);
}

/* 페이징 */
.paging {
    display: flex;
    justify-content: center;
    gap: 8px;
    margin-top: 28px;
}
.page-btn {
    padding: 5px 7px;
    border-radius: 8px;
    border: 1px solid #ccc;
    background: #fff;
    cursor: pointer;
    transition: .2s;
    display: inline-block;
}
.page-btn:hover {
    border-color: var(--accent);
    color: var(--accent);
}
.page-btn.active {
    background: var(--accent);
    color: #fff;
    border-color: var(--accent);
}

/* ===========================
   게시글 상세
=========================== */
.board-detail-section {
    max-width: 1280px;
    margin: 0 auto;
    padding: 60px 32px 120px;
}

.glass-box {
    background: rgba(255,255,255,0.35);
    backdrop-filter: blur(18px);
    border: 1px solid rgba(255,255,255,0.25);
    border-radius: 16px;
    padding: 24px 30px;
    margin-bottom: 40px;
}

.detail-table {
    width: 100%;
    border-collapse: collapse;
}

.detail-table th {
    width: 120px;
    padding: 14px 10px;
    background: rgba(255,255,255,0.5);
    border-bottom: 1px solid #ddd;
    font-weight: 600;
}
.detail-table td {
    padding: 14px 10px;
    border-bottom: 1px solid #eee;
}

.btn-area {
    text-align: right;
    padding-top: 20px;
}

/* ===========================
   파일 목록
=========================== */
.section-title {
    font-size: 20px;
    font-weight: 700;
    margin: 14px 0;
}

.file-table {
    width: 100%;
    border-collapse: collapse;
}
.file-table th {
    padding: 10px 8px;
    border-bottom: 1px solid #ccc;
    background: rgba(255,255,255,0.45);
}
.file-table td {
    padding: 12px 8px;
    border-bottom: 1px solid #eee;
}

.file-thumb {
    width: 90px;
    height: 90px;
    object-fit: cover;
    border-radius: 12px;
    border: 1px solid #ddd;
}

/* ===========================
   댓글 목록
=========================== */
.comment-list-box {
    padding: 24px 30px;
}

.comment-item-wrap {
    display: flex;
    flex-wrap: wrap;
    width: 100%;
    margin-bottom: 16px;
}

.reply-indent {
    width: 20px;
}

.comment-item-table {
    width: 100%;
    border-collapse: collapse;
}

.comment-writer-cell {
    padding: 10px 8px;
    font-weight: 600;
}
.comment-date-cell {
    padding: 10px 8px;
    color: #666;
    font-size: 13px;
}

.comment-content-cell {
    padding: 12px 8px;
}
.comment-content {
    line-height: 1.5;
    font-size: 14px;
    color: #444;
}

.comment-action-left,
.comment-action-right {
    padding: 10px 6px;
}

/* ===========================
   답글 / 재답글 입력 폼
=========================== */
.comment-reply-table {
    width: 100%;
    margin-top: 12px;
    border-collapse: collapse;
    border-radius: 14px;
    padding: 16px 20px !important;
}

.comment-input {
    width: 100%;
    height: 42px;
    border-radius: 10px;
    border: 1px solid #ddd;
    padding: 0 12px;
    outline: none;
    transition: .2s;
}
.comment-input:focus {
    border-color: #0A84FF;
}

.comment-textarea {
    width: 100%;
    border-radius: 10px;
    padding: 12px;
    border: 1px solid #ddd;
    resize: none;
    outline: none;
    transition: .2s;
}
.comment-textarea:focus {
    border-color: #0A84FF;
}

.comment-reply-btn-area {
    padding-top: 10px;
}


/* ===============================
   재답글(대댓글) 들여쓰기 스타일
=============================== */

/* 들여쓰기 기본 박스 */
.reply-indent {
    width: 28px; 
    position: relative;
}

/* 세로 라인(옵션) */
.reply-indent::before {
    content: "";
    position: absolute;
    left: 12px;
    top: 0;
    bottom: 0;

    width: 2px;
    background: rgba(0,0,0,0.08);
    border-radius: 2px;
}

/* 재답글 테이블에도 살짝 여백 추가 */
.comment-item-table {
    margin-left: 0;
}

.reply-indent + table.comment-item-table {
    margin-left: 12px; /* 실제 들여쓰기 효과 */
}

/* 재답글에 배경 강조(선택 사항) */
.reply-indent + table.comment-item-table {
    background: rgba(255,255,255,0.22); /* 아주 미세한 구분 */
    border-radius: 14px;
}

/* 재답글(대댓글) 작성자 앞에 ▶ 아이콘 붙이기 */
.reply-indent + table.comment-item-table .comment-writer::before {
    content: "▶ ";
    color: #555;
    font-size: 12px;
    margin-right: 4px;
    display: inline-block;
    transform: translateY(-1px);
}



/* 게시글 수정 */



