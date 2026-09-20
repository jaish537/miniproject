
-- PracTrack Database


-- =========================================
-- 1. STUDENTS TABLE
-- =========================================

CREATE TABLE students (
    student_id NUMBER PRIMARY KEY,
    name VARCHAR2(100) NOT NULL,
    roll_no VARCHAR2(30) UNIQUE NOT NULL,
    email VARCHAR2(100) UNIQUE NOT NULL,
    password VARCHAR2(100) NOT NULL,
    semester NUMBER,
    section VARCHAR2(20)
);


-- =========================================
-- 2. TEACHERS TABLE
-- =========================================

CREATE TABLE teachers (
    teacher_id NUMBER PRIMARY KEY,
    name VARCHAR2(100) NOT NULL,
    email VARCHAR2(100) UNIQUE NOT NULL,
    password VARCHAR2(100) NOT NULL
);


-- =========================================
-- 3. SUBJECTS TABLE
-- =========================================

CREATE TABLE subjects (
    subject_id NUMBER PRIMARY KEY,
    subject_name VARCHAR2(100) NOT NULL,
    semester NUMBER
);


-- =========================================
-- 4. PRACTICALS TABLE
-- =========================================

CREATE TABLE practicals (
    practical_id NUMBER PRIMARY KEY,
    subject_id NUMBER,
    practical_no NUMBER,
    title VARCHAR2(200),
    description CLOB,
    deadline DATE,

    CONSTRAINT fk_practical_subject
    FOREIGN KEY (subject_id)
    REFERENCES subjects(subject_id)
);


-- =========================================
-- 5. SUBMISSIONS TABLE
-- =========================================

CREATE TABLE submissions (
    submission_id NUMBER PRIMARY KEY,
    student_id NUMBER,
    practical_id NUMBER,
    file_name VARCHAR2(255),
    file_path VARCHAR2(500),
    submitted_at TIMESTAMP DEFAULT SYSTIMESTAMP,
    status VARCHAR2(30) DEFAULT 'SUBMITTED',

    CONSTRAINT fk_submission_student
    FOREIGN KEY (student_id)
    REFERENCES students(student_id),

    CONSTRAINT fk_submission_practical
    FOREIGN KEY (practical_id)
    REFERENCES practicals(practical_id)
);


-- =========================================
-- 6. REVIEWS TABLE
-- =========================================

CREATE TABLE reviews (
    review_id NUMBER PRIMARY KEY,
    submission_id NUMBER,
    teacher_id NUMBER,
    status VARCHAR2(30),
    remarks CLOB,
    reviewed_at TIMESTAMP DEFAULT SYSTIMESTAMP,

    CONSTRAINT fk_review_submission
    FOREIGN KEY (submission_id)
    REFERENCES submissions(submission_id),

    CONSTRAINT fk_review_teacher
    FOREIGN KEY (teacher_id)
    REFERENCES teachers(teacher_id)
);


-- =========================================
-- 7. NOTIFICATIONS TABLE
-- =========================================

CREATE TABLE notifications (
    notification_id NUMBER PRIMARY KEY,
    user_type VARCHAR2(20),
    user_id NUMBER,
    message CLOB,
    is_read NUMBER(1) DEFAULT 0,
    created_at TIMESTAMP DEFAULT SYSTIMESTAMP
);


-- =========================================
-- SEQUENCES
-- =========================================


-- Student Sequence

CREATE SEQUENCE student_seq
START WITH 1
INCREMENT BY 1;


-- Teacher Sequence

CREATE SEQUENCE teacher_seq
START WITH 1
INCREMENT BY 1;


-- Subject Sequence

CREATE SEQUENCE subject_seq
START WITH 1
INCREMENT BY 1;


-- Practical Sequence

CREATE SEQUENCE practical_seq
START WITH 1
INCREMENT BY 1;


-- Submission Sequence

CREATE SEQUENCE submission_seq
START WITH 1
INCREMENT BY 1;


-- Review Sequence

CREATE SEQUENCE review_seq
START WITH 1
INCREMENT BY 1;


-- Notification Sequence

CREATE SEQUENCE notification_seq
START WITH 1
INCREMENT BY 1;


-- =========================================
-- CHECK TABLES
-- =========================================

SELECT table_name FROM user_tables;


-- =========================================
-- CHECK SEQUENCES
-- =========================================

SELECT sequence_name FROM user_sequences;
