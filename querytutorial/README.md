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

### 共通カラム

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
		/* 抽出条件を組み立てる。 */
		QAuthor a = new QAuthor("a");
		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(a);

		/* 取出すカラムとデータの取出し方を指定してクエリを発行する。 */
		List<Tuple> list = queryDslJdbcOperations.query(query,
				new QTuple(a.all()));
```

### Beanとして取出す: QBean(QuerydslのAPI)を使う
```Java
		/* 抽出条件を組み立てる。 */
		QAuthor a = new QAuthor("a");
		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(a);

		/* 取出すカラムとデータの取出し方を指定してクエリを発行する。 */
		List<Author> list = queryDslJdbcOperations.query(query,
				new QBean<Author>(Author.class, a.all()));
```

### Beanとして取出す: RowMapper(Spring frameworkのAPI)を使う
```Java
		/* 抽出条件を組み立てる。 */
		QAuthor a = new QAuthor("a");
		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(a);

		/* 取出すカラムとデータの取出し方を指定してクエリを発行する。 */
		List<Author> list = queryDslJdbcOperations.query(query,
				rowMapperCreator.create(Author.class), a.all());
```

## SELECT句の書き方
### 照会するカラムを指定する
#### カラムを絞って照会する
```Java
		QAuthor a = new QAuthor("a");
		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(a);
		List<Tuple> list = queryDslJdbcOperations.query(query,
				new QTuple(a.id, a.loginId, a.name));
```

#### 全てのカラムを照会する
テーブルのメタデータの`all()`メソッドを使用してください。

```Java
		QAuthor a = new QAuthor("a");
		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(a);
		List<Tuple> list = queryDslJdbcOperations.query(query,
				new QTuple(a.all()));
```

#### アスタリスク「*」を指定して照会する
`Wildcard.all`(Wildcardクラスのstaticフィールド)を使用してください。ただし、この場合、得られるデータはObject[](Objectの配列)型となります。カラム名で値を参照することができないため、積極的な理由がない限りは、`Wildcard.all`の使用は避けてください。
基本的に、全カラムを照会する場合は、アスタリスクではなくテーブルのメタデータの`all()`メソッドを使用してください。

```Java
		QAuthor a = new QAuthor("a");
		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(a);
		List<Object[]> list = queryDslJdbcOperations.query(query, Wildcard.all);
```


#### カラムにエイリアス(別名)を付与する
カラムのメタデータの`as(エイリアス名)`メソッドを使用します。

```Java
		QAuthor a = new QAuthor("a");
		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(a);
		List<Tuple> list = queryDslJdbcOperations.query(query,
				new QTuple(a.id.as("alias1"), a.loginId.as("alias2"), a.name.as("alias3")));
```

### 定数値を指定する
#### 定数値をカラムとして照会する
`Expressions.constant(定数値)`メソッドを使用します。

```Java
		Expression<Integer> val1 = Expressions.constant(1);
		Expression<String> val2 = Expressions.constant("string");

		QAuthor a = new QAuthor("a");
		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(a);
		List<Tuple> list = queryDslJdbcOperations.query(query,
				new QTuple(a.id, val1, val2));
```

#### 定数値のカラムにエイリアス(別名)を付与する
`Expressions.as(定数カラムオブジェクト, エイリアス名)`メソッドを使用してください。
(カラムにエイリアス(別名)を付与する場合にも使えますが、カラムのエイリアスは前述の通りカラムのメタデータの`as(エイリアス名)`メソッドを使用してください)

```Java
		Expression<Integer> val1 = Expressions.constant(1);
		Expression<String> val2 = Expressions.constant("string");

		QAuthor a = new QAuthor("a");
		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(a);
		List<Tuple> list = queryDslJdbcOperations.query(query,
				new QTuple(a.id, Expressions.as(val1, "alias1"), Expressions.as(val2, "alias2")));
```

### カラム(または定数値)に対する算術計算
#### 加減乗除
数値カラムのメタデータ(`NumberExpression`クラス)に下記の算術計算メソッドが定義されています。これを使用してください。

|  #| 演算子  | メソッド名         |
|--:|:-------|:--------------------|
|  1| +       | `add(カラム)`      |
|  2| -       | `subtract(カラム)` |
|  3| *       | `multiply(カラム)` |
|  4| /       | `divide(カラム)`   |
|  5| mod     | `mod(カラム)`      |

#### 計算順序

### カラム(または定数値)に対する関数適用
#### 数値関数(インスタンスメソッド)
数値カラムのメタデータ(`NumberExpression`クラス)のインスタンスメソッドとして下記の数値関数が定義されています。これを使用してください。

|  #| 関数   | メソッド名 |
|--:|:-------|:-----------|
|  1| abs    | `abs()`    |
|  2| negate | `negate()` |
|  3| sqrt   | `sqrt()`   |
|  4| floor  | `floor()`  |
|  5| ceil   | `ceil()`   |
|  6| round  | `round()`  |

#### 数値関数(staticメソッド)
`MathExpressions`クラスのstaticメソッドとして下記の数値関数が定義されています。これを使用してください。

|  #| 関数    | メソッド名                             |
|--:|:--------|:---------------------------------------|
|  1| cos     | `cos(カラム)`                          |
|  2| sin     | `sin(カラム)`                          |
|  3| tan     | `tan(カラム)`                          |
|  4| cot     | `cot(カラム)`                          |
|  5| acos    | `acos(カラム)`                         |
|  6| asin    | `asin(カラム)`                         |
|  7| atan    | `atan(カラム)`                         |
|  8| cosh    | `cosh(カラム)`                         |
|  9| sinh    | `sinh(カラム)`                         |
| 10| tanh    | `tanh(カラム)`                         |
| 11| coth    | `coth(カラム)`                         |
| 12| degrees | `degrees(カラム)`                      |
| 13| radians | `radians(カラム)`                      |
| 14| exp     | `exp(カラム)`                          |
| 15| ln      | `ln(カラム)`                           |
| 16| log     | `log(カラム, 対数の底)`                |
| 17| power   | `power(カラム, べき乗数)`              |
| 18| max     | `max(カラム, カラム)`                  |
| 19| min     | `min(カラム, カラム)`                  |
| 20| random  | `random()`, `random(乱数の種)`         |
| 21| round   | `round(カラム)`, `round(カラム, 桁数)` |
| 22| sign    | `sign(カラム)`                         |


#### 文字列関数(インスタンスメソッド)
文字列カラムのメタデータ(`StringExpression`クラス)のインスタンスメソッドとして下記の文字列関数が定義されています。これを使用してください。

|  #| 関数       | メソッド名                  |
|--:|:-----------|:----------------------------|
|  1| concat     | `concat(カラム)`            |
|  2| substring  | `substring(カラム, カラム)` |
|  3| length     | `length()`                  |
|  4| lower      | `lower()`                   |
|  5| upper      | `upper()`                   |
|  6| trim       | `trim()`                    |


#### 文字列関数(staticメソッド)
`StringExpressions`クラスのstaticメソッドとして下記の文字列関数が定義されています。これを使用してください。

|  #| 関数    | メソッド名                                                       |
|--:|:--------|:-----------------------------------------------------------------|
|  1| ltrim   | `ltrim(カラム)`                                                  |
|  2| rtrim   | `rtrim(カラム)`                                                  |
|  3| lpad    | `lpad(カラム, 長さ(カラム))`, `lpad(カラム, 長さ(カラム), 文字)` |
|  4| rpad    | `rpad(カラム, 長さ(カラム))`, `rpad(カラム, 長さ(カラム), 文字)` |

#### 日時操作関数
`SQLExpressions`クラスのstaticメソッドとして下記の日時操作関数が定義されています。これを使用してください。

|  #| 関数       | メソッド名                           |
|--:|:-----------|:-------------------------------------|
|  1| dateadd    | `dateadd(加算部位, カラム, 加算値)`  |
|  2| datediff   | `datediff(差分部位, カラム, カラム)` |
|  3| datetrunc  | `datetrunc(切詰部位, カラム)`        |
|  4| addYears   | `addYears(カラム, 加算値)`           |
|  5| addMonths  | `addMonths(カラム, 加算値)`          |
|  6| addWeeks   | `addWeeks(カラム, 加算値)`           |
|  7| addDays    | `addDays(カラム, 加算値)`            |
|  8| addHours   | `addHours(カラム, 加算値)`           |
|  9| addMinutes | `addMinutes(カラム, 加算値)`         |
| 10| addSeconds | `addSeconds(カラム, 加算値)`         |

#### 集約関数
カラムのメタデータのインスタンスメソッドとして下記の集約関数が定義されています。これを使用してください。

|  #| 関数  | メソッド名 |
|--:|:------|:-----------|
|  1| count | `count()`  |
|  2| sum   | `sum()`    |
|  3| max   | `max()`    |
|  4| min   | `min()`    |

### CASE式を指定する

### スカラサブクエリを指定する

## FROM句の書き方
### 単一表を指定する

### 複数表を指定して結合する
#### 内部結合
#### 左外部結合
#### 右外部結合
#### 全外部結合

### SELECT文をFROM句に指定する

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
