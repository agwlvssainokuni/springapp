クエリの書き方チュートリアル
============================

使用するスキーマ
================
## テーブル
### 投稿者 author

| # | 論理名     | 物理名      | 型            |
|--:|:-----------|:------------|:--------------|
|  1| ID         | id          | BIGINT        |
|  2| ログインID | login_id    | VARCHAR(100)  |
|  3| 投稿者名   | name        | VARCHAR(100)  |

### TODO todo

| # | 論理名     | 物理名      | 型            |
|--:|:-----------|:------------|:--------------|
|  1| ID         | id          | BIGINT        |
|  2| 投稿者     | posted_by   | VARCHAR(100)  |
|  3| 投稿日時   | posted_at   | TIMESTAMP     |
|  4| 期日       | due_dt      | DATE          |
|  5| 完了日時   | done_at     | TIMESTAMP     |
|  6| 完了フラグ | done_flg    | INTEGER       |
|  7| TODO内容   | description | VARCHAR(1000) |

### 共通列

| # | 論理名           | 物理名       | 型        |
|--:|:-----------------|:-------------|:----------|
|  1| 更新日時         | updated_at   | TIMESTAMP |
|  2| 作成日時         | created_at   | TIMESTAMP |
|  3| ロックバージョン | lock_version | INTEGER   |
|  4| 削除フラグ       | deleted_flg  | INTEGER   |

## DBアクセス用クラス
Maven3プラグインを使用して「Querydsl SQL用メタデータ」と「MyBatis用マッパ」を作成します。
具体的には、下記のコマンドを発行します。

```bash:コマンドライン
$ mvn clean process-resources flyway:migrate
$ mvn querydsl:export
$ mvn mybatis-generator:generate
```


SELECT文
========

## 基本的なAPIの呼出し方
結果データをどのような形態で取り出すかにより、「Tupleとして取出す」「Beanとして取出す(QBeanを使う)」「Beanとして取出す(RowMapperを使う)」の3種類の呼出し方があります。これらの違いは「データの取出し方の指定の仕方の違い (および、取出し方に依存するに抽出するカラムの指定の仕方の違い)」です。
以下、それぞれの呼出しを例示します。

### Tupleとして取出す
```Java
		QAuthor a = new QAuthor("a");
		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(a);
		List<Tuple> list = queryDslJdbcOperations.query(query,
				new QTuple(a.all()));
```

### Beanとして取出す: QBean(QuerydslのAPI)を使う
```Java
		QAuthor a = new QAuthor("a");
		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(a);
		List<Author> list = queryDslJdbcOperations.query(query,
				new QBean<Author>(Author.class, a.all()));
```

### Beanとして取出す: RowMapper(Spring frameworkのAPI)を使う
```Java
		QAuthor a = new QAuthor("a");
		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(a);
		List<Author> list = queryDslJdbcOperations.query(query,
				rowMapperCreator.create(Author.class), a.all());
```

## SELECT句の書き方
### 照会する列(カラム)を指定する
#### 列を絞って照会する
```Java
		QAuthor a = new QAuthor("a");
		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(a);
		List<Tuple> list = queryDslJdbcOperations.query(query,
				new QTuple(a.id, a.loginId, a.name));
```

#### 全ての列を照会する
```Java
		QAuthor a = new QAuthor("a");
		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(a);
		List<Tuple> list = queryDslJdbcOperations.query(query,
				new QTuple(a.all()));
```

#### アスタリスク「*」を指定して照会する
```Java
		QAuthor a = new QAuthor("a");
		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(a);
		List<Object[]> list = queryDslJdbcOperations.query(query, Wildcard.all);
```
アスタリスク「*」(Wildcard.all)を指定して照会する場合、得られるデータはObject[](Objectの配列)型となります。列名で参照できないため、積極的な理由がない限りは、アスタリスク「*」を指定することはありません。
全列を照会する場合は、アスタリスクではなくメタデータの`all()`メソッドを使用してください。

#### 列にエイリアス(別名)を付与する
```Java
		QAuthor a = new QAuthor("a");
		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(a);
		List<Tuple> list = queryDslJdbcOperations.query(query,
				new QTuple(a.id.as("alias1"), a.loginId.as("alias2"), a.name.as("alias3")));
```

### 定数値を指定する
#### 定数値を列として照会する
```Java
		Expression<Integer> val1 = Expressions.constant(1);
		Expression<String> val2 = Expressions.constant("string");

		QAuthor a = new QAuthor("a");
		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(a);
		List<Tuple> list = queryDslJdbcOperations.query(query,
				new QTuple(a.id, val1, val2));
```

#### 定数の列にエイリアス(別名)を付与する
```Java
		Expression<Integer> val1 = Expressions.constant(1);
		Expression<String> val2 = Expressions.constant("string");

		QAuthor a = new QAuthor("a");
		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(a);
		List<Tuple> list = queryDslJdbcOperations.query(query,
				new QTuple(a.id, Expressions.as(val1, "alias1"), Expressions.as(val2, "alias2")));
```
定数にエイリアス(別名)を付与する場合は`Expressions.as(定数列オブジェクト, "エイリアス名")`メソッドを使用してください。(列にエイリアス(別名)を付与する場合とは異なります)

### 列(または定数値)に対する算術計算
#### 加減乗除
#### 計算順序

### 列(または定数値)に対する関数適用
#### 数学関数
#### 文字列関数
#### 日時操作関数
#### 集約関数

### CASE式を指定する

### スカラサブクエリを指定する

## FROM句の書き方
### 単一表を指定する

### 複数表を指定して結合する
#### 内部結合
#### 左外部結合
#### 右外部結合
#### 全外部結合

### SELECT文(フルクエリ)をFROM句に指定する

## WHERE句の書き方
### 条件の組合せ

### 比較

### 述語
#### LIKE
#### BETWEEN
#### EXISTS
#### IN(定数)
#### IN(サブクエリ)

## GROUP BY句の書き方

## HAVING句の書き方

## ORDER BY句の書き方

INSERT文
========

UPDATE文
========

DELETE文
========

以上。
