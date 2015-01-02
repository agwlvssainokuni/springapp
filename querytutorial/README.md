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

## 1. 基本的なAPIの呼出し方
結果データをどのような形態で取り出すかにより、「Tupleとして取出す」「Beanとして取出す(QBeanを使う)」「Beanとして取出す(RowMapperを使う)」の3種類の呼出し方があります。これらの違いは「データの取出し方の指定の仕方の違い (および、取出し方に依存するに抽出するカラムの指定の仕方の違い)」です。
以下、それぞれの呼出しを例示します。

### 1.1 Tupleとして取出す
```Java
		/* 抽出条件を組み立てる。 */
		QAuthor a = new QAuthor("a");
		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(a);

		/* 取出すカラムとデータの取出し方を指定してクエリを発行する。 */
		List<Tuple> list = queryDslJdbcOperations.query(query,
				new QTuple(a.all()));
```

### 1.2 Beanとして取出す: QBean(QuerydslのAPI)を使う
```Java
		/* 抽出条件を組み立てる。 */
		QAuthor a = new QAuthor("a");
		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(a);

		/* 取出すカラムとデータの取出し方を指定してクエリを発行する。 */
		List<Author> list = queryDslJdbcOperations.query(query,
				new QBean<Author>(Author.class, a.all()));
```

### 1.3 Beanとして取出す: RowMapper(Spring frameworkのAPI)を使う
```Java
		/* 抽出条件を組み立てる。 */
		QAuthor a = new QAuthor("a");
		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(a);

		/* 取出すカラムとデータの取出し方を指定してクエリを発行する。 */
		List<Author> list = queryDslJdbcOperations.query(query,
				rowMapperCreator.create(Author.class), a.all());
```

## 2. SELECT句の書き方
### 2.1 照会するカラムを指定する
#### 2.1.1 カラムを絞って照会する
```Java
		QAuthor a = new QAuthor("a");
		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(a);
		List<Tuple> list = queryDslJdbcOperations.query(query, new QTuple(a.id,
				a.loginId, a.name));
```

上記Javaコードは下記SQLに相当します。

```SQL
SELECT
	a.id,
	a.login_id,
	a.name
FROM
	author AS a
```

#### 2.1.2 全てのカラムを照会する
テーブルのメタデータの`all()`メソッドを使用してください。

```Java
		QAuthor a = new QAuthor("a");
		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(a);
		List<Tuple> list = queryDslJdbcOperations.query(query,
				new QTuple(a.all()));
```

上記Javaコードは下記SQLに相当します。

```SQL
SELECT
	a.id,
	a.login_id,
	a.name,
	a.updated_at,
	a.created_at,
	a.lock_version,
	a.deleted_flg
FROM
	author AS a
```

#### 2.1.3 アスタリスク「*」を指定して照会する
`Wildcard.all`(Wildcardクラスのstaticフィールド)を使用してください。ただし、この場合、得られるデータはObject[](Objectの配列)型となります。カラム名で値を参照することができないため、積極的な理由がない限りは、`Wildcard.all`の使用は避けてください。
基本的に、全カラムを照会する場合は、アスタリスクではなくテーブルのメタデータの`all()`メソッドを使用してください。

```Java
		QAuthor a = new QAuthor("a");
		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(a);
		List<Object[]> list = queryDslJdbcOperations.query(query, Wildcard.all);
```

上記Javaコードは下記SQLに相当します。

```SQL
SELECT
	*
FROM
	author AS a
```

#### 2.1.4 カラムにエイリアス(別名)を付与する
カラムのメタデータの`as(エイリアス名)`メソッドを使用します。

```Java
		QAuthor a = new QAuthor("a");
		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(a);
		List<Tuple> list = queryDslJdbcOperations.query(
				query,
				new QTuple(a.id.as("alias1"), a.loginId.as("alias2"), a.name
						.as("alias3")));
```

上記Javaコードは下記SQLに相当します。

```SQL
SELECT
	a.id		AS alias1,
	a.login_id	AS alias2,
	a.name		AS alias3
FROM
    author AS a
```

### 2.2 定数値を指定する
#### 2.2.1 定数値をカラムとして照会する
`Expressions.constant(定数値)`メソッドを使用します。

```Java
		Expression<Integer> const1 = Expressions.constant(1);
		Expression<String> const2 = Expressions.constant("string");

		QAuthor a = new QAuthor("a");
		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(a);
		List<Tuple> list = queryDslJdbcOperations.query(query, new QTuple(a.id,
				const1, const2));
```

上記Javaコードは下記SQLに相当します。

```SQL
SELECT
	a.id,
	1,
	'string'
FROM
	author AS a
```

#### 2.2.2 定数値のカラムにエイリアス(別名)を付与する
`Expressions.as(定数カラムオブジェクト, エイリアス名)`メソッドを使用してください。
(カラムにエイリアス(別名)を付与する場合にも使えますが、カラムのエイリアスは前述の通りカラムのメタデータの`as(エイリアス名)`メソッドを使用してください)

```Java
		Expression<Integer> const1 = Expressions.constant(1);
		Expression<String> const2 = Expressions.constant("string");

		QAuthor a = new QAuthor("a");
		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(a);
		List<Tuple> list = queryDslJdbcOperations.query(
				query,
				new QTuple(a.id, Expressions.as(const1, "alias1"), Expressions
						.as(const2, "alias2")));
```

上記Javaコードは下記SQLに相当します。

```SQL
SELECT
	a.id,
	1			AS alias1,
	'string'	AS alias2
FROM
    author AS a
```

### 2.3 カラム(または定数値)に対する算術計算
#### 2.3.1 加減乗除
数値カラムのメタデータ(`NumberExpression`クラス)に下記の算術計算メソッドが定義されています。これを使用してください。

|  #| 演算子    | メソッド           |
|--:|:----------|:-------------------|
|  1| +(加算)   | `add(カラム)`      |
|  2| -(減算)   | `subtract(カラム)` |
|  3| *(乗算)   | `multiply(カラム)` |
|  4| /(除算)   | `divide(カラム)`   |
|  5| MOD(剰余) | `mod(カラム)`      |

```Java
		QAuthor a = new QAuthor("a");
		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(a);
		List<Tuple> list = queryDslJdbcOperations.query(
				query,
				new QTuple(a.id, a.id.add(2L), a.id.subtract(2L), a.id
						.multiply(2L), a.id.divide(2L), a.id.mod(2L)));
```

上記Javaコードは下記SQLに相当します。

```SQL
SELECT
	a.id,
	a.id + 2,
	a.id - 2,
	a.id * 2,
	a.id / 2,
	MOD(a.id, 2)
FROM
	author AS a
```

#### 2.3.2 計算順序
複数の計算を組合わせる場合、「計算のメソッドを呼出した順序」に従って計算されます。いわゆる四則演算の優先度には従いませんので注意してください。
例えば、「`メタデータA.add(メタデータB).multiply(2)`」は「`(カラムA + カラムB) * 2`」として計算されます。「`カラムA + (カラムB * 2)`」を計算するには「`メタデータA.add(メタデータB.multiply(2))`」の形で指定してください。

```Java
		QAuthor a = new QAuthor("a");
		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(a);
		List<Tuple> list = queryDslJdbcOperations.query(query,
				new QTuple(a.id, a.id.add(2L).multiply(2L), a.id.multiply(2L)
						.add(2L), a.id.add(2L).multiply(2L).subtract(2L)
						.divide(2L), a.id.add(2L).multiply(a.id.subtract(2L))));
```

上記Javaコードは下記SQLに相当します。

```SQL
SELECT
	a.id,
	(a.id + 2) * 2,
	a.id * 2 + 2,
	((a.id + 2) * 2 - 2) / 2,
	(a.id + 2) * (a.id - 2)
FROM
	author AS a
```

### 2.4 カラム(または定数値)に対する関数適用
#### 2.4.1 関数適用
SQL関数は、カラムのメタデータのインスタンスメソッド、または、staticメソッドとして定義されています。これらの呼出しによりSQL関数の適用を表現します。

```Java
		QAuthor a = new QAuthor("a");
		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(a);
		List<Tuple> list = queryDslJdbcOperations.query(
				query,
				new QTuple(a.id, a.loginId, a.name, a.loginId.length(),
						a.loginId.concat(a.name), StringExpressions.lpad(
								a.loginId, 10, 'X')));
```

上記Javaコードは下記SQLに相当します。

```SQL
SELECT
	a.id,
	a.login_id,
	a.name,
	LENGTH(a.login_id),
	CONCAT(a.login_id, a.name),
	LPAD(a.login_id, 10, 'X')
FROM
	author AS a
```

#### 2.4.2 数値関数(インスタンスメソッド)
数値カラムのメタデータ(`NumberExpression`クラス)のインスタンスメソッドとして下記の数値関数が定義されています。これを使用してください。

|  #| 関数              | メソッド   |
|--:|:------------------|:-----------|
|  1| ABS(絶対値)       | `abs()`    |
|  2| 符号反転          | `negate()` |
|  3| SQRT(平方根)      | `sqrt()`   |
|  4| FLOOR(整数切下げ) | `floor()`  |
|  5| CEIL(整数切上げ)  | `ceil()`   |
|  6| ROUND(四捨五入)   | `round()`  |

#### 2.4.3 数値関数(staticメソッド)
`MathExpressions`クラスのstaticメソッドとして下記の数値関数が定義されています。これを使用してください。

|  #| 関数               | メソッド                               |
|--:|:-------------------|:---------------------------------------|
|  1| COS(余弦)          | `cos(カラム)`                          |
|  2| SIN(正弦)          | `sin(カラム)`                          |
|  3| TAN(正接)          | `tan(カラム)`                          |
|  4| COT(余接)          | `cot(カラム)`                          |
|  5| ACOS(余弦の逆関数) | `acos(カラム)`                         |
|  6| ASIN(正弦の逆関数) | `asin(カラム)`                         |
|  7| ATAN(正接の逆関数) | `atan(カラム)`                         |
|  8| COSH(双曲線余弦)   | `cosh(カラム)`                         |
|  9| SINH(双曲線正弦)   | `sinh(カラム)`                         |
| 10| TANH(双曲線正接)   | `tanh(カラム)`                         |
| 11| COTH(双曲線余接)   | `coth(カラム)`                         |
| 12| ラジアン→度        | `degrees(カラム)`                      |
| 13| 度→ラジアン        | `radians(カラム)`                      |
| 14| EXP(指数関数)      | `exp(カラム)`                          |
| 15| LN(自然対数)       | `ln(カラム)`                           |
| 16| LOG(対数)          | `log(カラム, 対数の底)`                |
| 17| POWER(べき乗)      | `power(カラム, べき乗数)`              |
| 18| MAX(最大)          | `max(カラム, カラム)`                  |
| 19| MIN(最小)          | `min(カラム, カラム)`                  |
| 20| RANDOM(乱数)       | `random()`, `random(乱数の種)`         |
| 21| ROUND(四捨五入)    | `round(カラム)`, `round(カラム, 桁数)` |
| 22| SIGN(符号)         | `sign(カラム)`                         |


#### 2.4.4 文字列関数(インスタンスメソッド)
文字列カラムのメタデータ(`StringExpression`クラス)のインスタンスメソッドとして下記の文字列関数が定義されています。これを使用してください。

|  #| 関数                  | メソッド                    |
|--:|:----------------------|:----------------------------|
|  1| CONCAT(文字列結合)    | `concat(カラム)`            |
|  2| SUBSTRING(部分文字列) | `substring(カラム, カラム)` |
|  3| LENGTH(文字列長)      | `length()`                  |
|  4| LOWER(小文字変換)     | `lower()`                   |
|  5| UPPER(大文字変換)     | `upper()`                   |
|  6| TRIM(空白文字除去)    | `trim()`                    |


#### 2.4.5 文字列関数(staticメソッド)
`StringExpressions`クラスのstaticメソッドとして下記の文字列関数が定義されています。これを使用してください。

|  #| 関数                    | メソッド                                                         |
|--:|:------------------------|:-----------------------------------------------------------------|
|  1| LTRIM(先頭空白文字除去) | `ltrim(カラム)`                                                  |
|  2| RTRIM(末尾空白文字除去) | `rtrim(カラム)`                                                  |
|  3| LPAD(先頭空白文字追加)  | `lpad(カラム, 長さ(カラム))`, `lpad(カラム, 長さ(カラム), 文字)` |
|  4| RPAD(末尾空白文字追加)  | `rpad(カラム, 長さ(カラム))`, `rpad(カラム, 長さ(カラム), 文字)` |

#### 2.4.6 日時操作関数(インスタンスメソッド)
`DateExpression`クラス、`TimeExpression`クラス、`DateTimeExpression`クラスのインスタンスメソッドとして下記の日時操作関数が定義されています。これを使用してください。

|  #| 関数                         | メソッド        |
|--:|:-----------------------------|:----------------|
|  1| 年                           | `year()`        |
|  2| 月(1-12; JAN-DEC)            | `month()`       |
|  3| 週                           | `week()`        |
|  4| 当年の中の日付(1-366)        | `dayOfYear()`   |
|  5| 当月の中の日付(1-31)         | `dayOfMonth()`  |
|  6| 当週の中の日付(1-7; SUN-SAT) | `dayOfWeek()`   |
|  7| 時(0-23)                     | `hour()`        |
|  8| 分(0-59)                     | `minute()`      |
|  9| 秒(0-59)                     | `second()`      |
| 10| ミリ秒(0-999)                | `milliSecond()` |

#### 2.4.7 日時操作関数(staticメソッド)
`SQLExpressions`クラスのstaticメソッドとして下記の日時操作関数が定義されています。これを使用してください。

|  #| 関数     | メソッド                             |
|--:|:---------|:-------------------------------------|
|  1| 日時加算 | `dateadd(加算部位, カラム, 加算値)`  |
|  2| 日時差分 | `datediff(差分部位, カラム, カラム)` |
|  3| 日時切詰 | `datetrunc(切詰部位, カラム)`        |
|  4| 年加算   | `addYears(カラム, 加算値)`           |
|  5| 月加算   | `addMonths(カラム, 加算値)`          |
|  6| 週加算   | `addWeeks(カラム, 加算値)`           |
|  7| 日加算   | `addDays(カラム, 加算値)`            |
|  8| 時加算   | `addHours(カラム, 加算値)`           |
|  9| 分加算   | `addMinutes(カラム, 加算値)`         |
| 10| 秒加算   | `addSeconds(カラム, 加算値)`         |

また、`DateExpression`クラス、`TimeExpression`クラス、`DateTimeExpression`クラスのstaticメソッドとして下記の日時操作関数が定義されています。これを使用してください。

|  #| 関数                        | メソッド                                                  |
|--:|:----------------------------|:----------------------------------------------------------|
|  1| CURRENT_DATE(現在日付)      | `DateExpression.currentDate(LocalDate.class)`             |
|  2| CURRENT_TIME(現在時刻)      | `TimeExpression.currentTime(LocalTime.class)`             |
|  3| CURRENT_TIMESTAMP(現在日時) | `DateTimeExpression.currentDateTime(LocalDateTime.class)` |

#### 2.4.8 集約関数(インスタンスメソッド)
カラムのメタデータのインスタンスメソッドとして下記の集約関数が定義されています。これを使用してください。

|  #| 関数        | メソッド  |
|--:|:------------|:----------|
|  1| COUNT(件数) | `count()` |
|  2| SUM(合計)   | `sum()`   |
|  3| MAX(最大)   | `max()`   |
|  4| MIN(最小)   | `min()`   |

```Java
		QTodo a = new QTodo("a");
		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(a);
		query.groupBy(a.postedBy);
		List<Tuple> list = queryDslJdbcOperations.query(query, new QTuple(
				a.postedBy, a.id.count(), a.id.sum(), a.postedAt.min(),
				a.postedAt.max()));
```

上記Javaコードは下記SQLに相当します。

```SQL
SELECT
	a.posted_by,
	COUNT(a.id),
	SUM(a.id),
	MIN(a.posted_at),
	MAX(a.posted_at)
FROM
	todo AS a
GROUP BY
	a.posted_by
```

### 2.5 CASE式を指定する
#### 2.5.1 単純CASE式
カラムのメタデータのインスタンスメソッドを使用してCASE式を組み立てください。
具体的には、`メタデータ.when(条件値).then(結果値)...when(条件値).then(結果値).otherwise(結果値)`を使用してCASE式を組み立てます。

```Java
		/* 抽出条件を組み立てる。 */
		QTodo a = new QTodo("a");
		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(a);

		/* CASE式を組立てる。 */
		Expression<String> doneDesc = a.doneFlg.when(0).then("未実施").when(1)
				.then("実施済").otherwise("不定");

		/* 取出すカラムとデータの取出し方を指定してクエリを発行する。 */
		List<Tuple> list = queryDslJdbcOperations.query(query, new QTuple(a.id,
				a.doneFlg, doneDesc));
```

上記Javaコードは下記SQLに相当します。

```SQL
SELECT
	a.id,
	a.done_flg,
	CASE a.done_flg
		WHEN 0 THEN '未実施'
		WHEN 1 THEN '実施済'
		ELSE '不定'
	END
FROM
	todo AS a
```

#### 2.5.2 検索CASE式
`CaseBuilder`クラスを使用してCASE式を組み立ててください。
具体的には、`Expressions.cases()`メソッドにより`CaseBuilder`インスタンスを生成し、`Expressions.cases().when(条件式).then(結果値)...when(条件式).then(結果値).otherwise(結果値)`の形でCASE式を組み立てます。

```Java
		/* 抽出条件を組み立てる。 */
		QTodo a = new QTodo("a");
		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(a);

		/* CASE式を組立てる。 */
		Expression<String> doneDesc = Expressions.cases().when(a.doneFlg.eq(1))
				.then("実施済").when(a.dueDt.lt(new LocalDate(2015, 2, 1)))
				.then("未実施(期限内)").otherwise("未実施(期限切)");

		/* 取出すカラムとデータの取出し方を指定してクエリを発行する。 */
		List<Tuple> list = queryDslJdbcOperations.query(query, new QTuple(a.id,
				a.dueDt, a.doneFlg, doneDesc));
```

上記Javaコードは下記SQLに相当します。

```SQL
SELECT
	a.id,
	a.due_dt,
	a.done_flg,
	CASE
		WHEN a.done_flg = 1 THEN '実施済'
		WHEN a.due_dt < '2014-02-01' THEN '未実施(期限内)'
		ELSE '未実施(期限切)'
	END
FROM
	todo AS a
```


### 2.6 スカラサブクエリを指定する
`SQLSubQuery`クラスを使用してサブクエリを組み立てください。`SQLSubQuery`の使い方は`SQLQuery`に準じます。

```Java
		/* 抽出条件を組み立てる。 */
		QTodo a = new QTodo("a");
		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(a);

		/* スカラサブクエリを組立てる。 */
		QAuthor b = new QAuthor("b");
		Expression<String> posterName = new SQLSubQuery().from(b)
				.where(b.loginId.eq(a.postedBy), b.deletedFlg.eq(0))
				.unique(b.name);

		/* 取出すカラムとデータの取出し方を指定してクエリを発行する。 */
		List<Tuple> list = queryDslJdbcOperations.query(query, new QTuple(a.id,
				a.postedBy, posterName));
```

上記Javaコードは下記SQLに相当します。

```SQL
SELECT
	a.id,
	a.posted_by,
	(
		SELECT
			b.name
		FROM
			author AS b
		WHERE
			b.login_id = a.posted_by
			AND
			b.deleted_flg = 0
	)
FROM
	todo AS a
```

## 3. FROM句の書き方
### 3.1 単一表を指定する

### 3.2 複数表を指定して結合する
#### 3.2.1 内部結合
#### 3.2.2 左外部結合
#### 3.2.3 右外部結合
#### 3.2.4 全外部結合

### 3.3 SELECT文をFROM句に指定する

## 4. WHERE句の書き方
### 4.1 条件の組合せ

### 4.2 比較

### 4.3 述語
#### 4.3.1 LIKE
#### 4.3.2 IS NULL
#### 4.3.3 BETWEEN
#### 4.3.4 EXISTS
#### 4.3.5 IN(定数)
#### 4.3.6 IN(サブクエリ)

## 5. GROUP BY句とHAVING句の書き方

## 6. ORDER BY句の書き方

INSERT文
========

UPDATE文
========

DELETE文
========

以上。
