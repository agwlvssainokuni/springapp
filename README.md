SpringApp
=========

プロジェクト概要
----------------
Spring framework、MyBatis、Gradle、Logbackをどう使っていくかを、プロジェクトのテンプレートの形でまとめます。

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
		*	実行系
			*	キューに入れる
				*	JmsTemplate (JmsOperations)
			*	キューから受け取って実行
				*	MDP (Message-Driven POJOs)
				*	jms:listener-container
				*	jms:listener
		*	非同期実行の状態管理
			*	キュー処理と状態管理のトランザクションを分ける
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
	*	Spring framework統合
		*	org.mybatis.spring.SqlSessionFactoryBean
	*	MyBatis Generator
		*	ANTタスクをGradleから呼び出し
			*	org.mybatis.generator.ant.GeneratorAntTask
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

ライセンス
==========
> Copyright 2014 agwlvssainokuni
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
