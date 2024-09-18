DROP TABLE IF EXISTS recipe;

-- レシピテーブル
CREATE TABLE recipe (
    id SERIAL PRIMARY KEY,
    title TEXT,
    introduction TEXT,
    serving INTEGER,
    ingredients TEXT,
    detail TEXT,
    point TEXT,
    image_path TEXT,
    display_flag BOOLEAN DEFAULT false
);

DROP TABLE IF EXISTS users;

-- ユーザーテーブル
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    name TEXT,
    email TEXT,
    password TEXT
);

DROP TABLE IF EXISTS likes;
-- いいねテーブル
CREATE TABLE likes (
    id SERIAL PRIMARY KEY,
    user_id INT NOT NULL,
    recipe_id INT NOT NULL,
    display_flag BOOLEAN DEFAULT true,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UNIQUE (user_id, recipe_id)
);

-- テストユーザー1
INSERT INTO
    users (name, email, password)
VALUES (
        'test',
        'test@mail',
        'testtest'
    );
-- テストユーザー2
INSERT INTO
    users (name, email, password)
VALUES (
        'test2',
        'test2@mail',
        'testtest'
    );

-- テストレシピ
INSERT INTO
    recipe (
        title,
        introduction,
        serving,
        ingredients,
        detail,
        point,
        image_path
    )
VALUES (
        'テスト料理',
        'てすとてすとてすと',
        1,
        'テスト: 1個, てすと: 2個',
        'テストを1口大に切り、煮込みます。てすとをみじん切りします',
        'てすとは炒めすぎない',
        'data:image/png;base64,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'
    );