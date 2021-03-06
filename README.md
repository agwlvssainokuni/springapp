SpringApp
=========

プロジェクト概要
----------------
Spring framework、MyBatis、Querydsl、Gradle、Logbackをどう使っていくかを、プロジェクトのテンプレートの形でまとめます。

検証課題
--------
*	Spring framework
	*	AOP
		*	トレース (自前)
	*	DBアクセス
		*	Spring JDBC
			*	名前付きパラメタによるSQL記述
			*	RowMapper<T>
				*	BeanPropertyRowMapper<T>
			*	カーソル制御を想定したDBアクセスの典型的実装パターン
		*	トランザクション制御
			*	annotation-driven
	*	JMS
		*	ConnectionFactory
			*	jee:jndi-lookup
		*	JndiDestinationResolver
		*	TaskExecutor
			*	DefaultManagedTaskExecutor
		*	実行系
			*	キューに入れる
				*	JmsTemplate (JmsOperations)
			*	キューから受け取って実行
				*	MDP (Message-Driven POJOs)
				*	jms:listener-container
				*	jms:listener
		*	非同期実行の状態管理
			*	キュー処理と状態管理のトランザクションを分ける
		*	テストケース向けJmsOperations解決
			*	Mockitoで作成するモックをbeanとして定義
				*	factory-method=mock
				*	constructor-arg=JmsOperations
	*	メッセージ定義
		*	MessageSourceの初期化
	*	ロケール解決
		*	ロケール切替え
		*	ロケール受渡し
	*	Spring MVC
		*	実装パターン
			*	@Controller, @RequestMapping, @ModelAttribute
			*	URI、ビュー名の定数定義
			*	アクションのメソッドの引数の典型的実装パターン
		*	ビュー
			*	JSTL
			*	Apache Tiles 3
			*	Jackson 2
				*	JSON
				*	XML
				*	YAML
		*	データバインディング
			*	型変換のカスタマイズ
				*	Parser
				*	Printer
		*	バリデーション
			*	Spring Validator
				*	@InitBinder
			*	Bean Validation (JSR-303)
				*	カスタムバリデーション
				*	@ReportAsSingleViolation
		*	ページネーション
			*	リンクの生成
				*	JSPタグでページネーションリンクを生成。
			*	クリック処理&ON/OFFをJavaScriptで組込み
		*	ファイルアップロード
		*	ファイルダウンロード
		*	ハンドラインタセプタ
			*	操作ログ (自前)
	*	Spring Security
		*	Webアプリへの組込み
		*	認証プロバイダ
		*	パスワードエンコード
		*	Authentication受渡し
	*	Spring Mobile
		*	ビュー切替え (normal, tablet, mobile)
		*	サイトプリファレンス切替え
		*	サイトプリファレンス受渡し
	*	バッチフレームワーク (自前)
*	Gradle
	*	複数プロジェクト管理
		*	管理系Web、公開系Web、バッチの3つのプロジェクトが、1つの共通プロジェクトを参照。
			*	Eclipseに依存プロジェクトとして認識させる
		*	依存ライブラリの管理
			*	Eclipseに依存ライブラリを認識させる。
			*	共通プロジェクトの依存ライブラリをEclipseで伝播させる。
	*	デリバリ向けビルドスクリプト
		*	デリバリ向けリソースファイル生成
		*	バッチ起動スクリプト生成
		*	デリバリ向けアーカイブ作成
*	MyBatis
	*	SqlMapper (XML)
		*	ダイナミックSQL
	*	カスタマイズ設定
		*	Joda-Timeライブラリ対応
			*	TypeHandler
	*	Spring framework統合
		*	org.mybatis.spring.SqlSessionFactoryBean
	*	MyBatis Generator
		*	Mavenタスクで生成
			*	org.mybatis.generator:mybatis-generator-maven-plugin
		*	DBをFlywayでセットアップ
*	Querydsl
	*	標準API
		*	SQLQueryFactory
		*	SQLQuery, SQLInsertClause, SQLUpdateClause, SQLDeleteClause
	*	カスタマイズ設定
		*	Joda-Timeライブラリ対応
			*	標準APIにJoda-Time対応はあるが、タイムゾーンの扱いを調整する場合に自前で用意。
	*	Spring framework統合 (自前でDB接続制御を追加)
		*	Provider<Connection>
			*	DataSourceUtils.getConnection(DataSource) を呼ぶ。
		*	SQLDetailedListener
			*	DataSourceUtils.releaseConnection(Connection, DataSource) を呼ぶ。
	*	Querydsl codegen
		*	Mavenタスクで生成。
			*	com.mysema.querydsl:querydsl-maven-plugin
		*	DBをFlywayでセットアップ
*	Logback
	*	コンソール出力
	*	ファイル出力
	*	ファイルのローテーション
		*	サイズ
		*	日次
		*	保持履歴数
		*	圧縮
	*	プロパティファイルでパラメタを外出し
	*	自前Appender
		*	Fluent
*	その他
	*	CSV入出力
	*	エクスポート、インポート
	*	ログ出力
		*	ログID定義
		*	ログ文言定義
	*	Lombok (http://projectlombok.org)
		*	build.gradle に組込み
			*	親プロジェクトでまとめて provided を定義
				*	コンパイル時のみ組込み
				*	実行時、パッケージ時は不要
				*	Eclipse向けには参照ライブラリー (eclipse.classpath の plusConfigurations) に追加
			*	warプラグインの providedCompile 相当
				*	ommon, batch は war プラグインを入れないため独自に provided を追加
				*	動きを合わせるため admin, entree も provided で指定 (providedCompile は使わず)
		*	IDE (Eclipse) に組込み
			*	インストーラを実行するだけ (java -jar lombok.jar)
		*	@Getter, @Setter, @EqualsAndHashCode, @ToString

ライセンス
==========
> Copyright 2014,2015 agwlvssainokuni
>
> Licensed under the Apache License, Version 2.0 (the "License");
> you may not use this file except in compliance with the License.
> You may obtain a copy of the License at
>
>     http://www.apache.org/licenses/LICENSE-2.0
>
> Unless required by applicable law or agreed to in writing, software
> distributed under the License is distributed on an "AS IS" BASIS,
> WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
> See the License for the specific language governing permissions and
> limitations under the License.
