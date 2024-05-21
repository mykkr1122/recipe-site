DROP TABLE IF EXISTS receipe;

CREATE TABLE receipe(
    id SERIAL PRIMARY KEY,
    title TEXT,
    introduction TEXT,
    ingredient TEXT,
    detail TEXT,
    point TEXT,
    image_path TEXT,
    display_flag BOOLEAN
); 

DROP TABLE IF EXISTS users;

CREATE TABLE users(
    id SERIAL PRIMARY KEY,
    name TEXT,
    email TEXT,
    password TEXT
);

-- テストユーザー
INSERT INTO users
(name, email, password)
VALUES
('test', 'test@mail', 'testtest');

-- テストレシピ
INSERT INTO receipe
(title, introduction, ingredient, detail, point, image_path, display_flag)
VALUES
('テスト料理', 'てすとてすとてすと', 'テスト　…　1個, てすと　…　2個', 'テストを1口大に切り、煮込みます。てすとをみじん切りします', 'てすとは炒めすぎない', 'test.png', false);