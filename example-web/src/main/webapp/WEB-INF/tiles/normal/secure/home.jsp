<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="foundation" uri="urn:cherry:foundation"%>
<%@ taglib prefix="ex" tagdir="/WEB-INF/tags"%>
<c:url var="baseUri" value="/secure" />
<h2 class="page-header">HOME</h2>
<ul class="nav nav-tabs togglable-tabs" role="tablist">
	<li role="presentation"><a href="#basic" aria-controls="basic" role="tab" data-toggle="tab">基本画面遷移事例</a></li>
	<li role="presentation"><a href="#applied" aria-controls="applied" role="tab" data-toggle="tab">応用画面遷移事例</a></li>
	<li role="presentation"><a href="#form" aria-controls="form" role="tab" data-toggle="tab">入力フォーム事例</a></li>
	<li role="presentation"><a href="#validation" aria-controls="validation" role="tab" data-toggle="tab">単項目チェック事例</a></li>
</ul>
<div class="tab-content">
	<div id="basic" class="tab-pane" role="tabpanel">
		<ul class="list-group list-unstyled">
			<li><a href="${baseUri}/basic/ex10/" class="list-group-item">単票入力系1: 登録入力 - 登録確認 - 登録完了 - 変更入力 - 変更確認 - 変更完了</a></li>
			<li><a href="${baseUri}/basic/ex20/" class="list-group-item">単票入力系2: 登録入力 - 登録確認 - 変更入力(登録完了メッセージ) - 変更確認 - 変更入力(変更完了メッセージ)</a></li>
			<li><a href="${baseUri}/basic/ex30/" class="list-group-item">検索一覧系1: 検索条件入力 - 検索結果一覧 - 単票変更入力 - 単票変更確認 - 単票変更完了 - 検索結果一覧または検索条件入力</a></li>
			<li><a href="${baseUri}/basic/ex40/" class="list-group-item">検索一覧系2: 検索条件入力 - 検索結果一覧 - 単票変更入力 - 単票変更確認 - 単票変更入力(変更完了メッセージ) -
					検索結果一覧または検索条件入力</a></li>
			<li><a href="${baseUri}/basic/ex50/" class="list-group-item">一括変更系1: 検索条件入力 - 検索結果一覧(対象選択) - 一括変更入力 - 一括変更確認 - 一括変更完了 -
					検索結果一覧または検索条件入力</a></li>
			<li><a href="${baseUri}/basic/ex60/" class="list-group-item">一括変更系2: 検索条件入力 - 検索結果一覧(対象選択) - 一括変更入力 - 一括変更確認 - 一括変更入力(変更完了メッセージ) -
					検索結果一覧または検索条件入力</a></li>
			<li><a href="${baseUri}/basic/ex90/" class="list-group-item">アップロード系1: 入力 - 完了</a></li>
		</ul>
	</div>
	<div id="applied" class="tab-pane" role="tabpanel">
		<ul class="list-group list-unstyled">
			<li><a href="${baseUri}/applied/ex10/" class="list-group-item">単票入力系1: 登録入力 - 補助入力 - 補助確認 - 登録入力 - 登録確認 - 登録完了 - 変更入力 - 補助入力 - 補助確認
					- 変更入力 - 変更確認 - 変更完了</a></li>
			<li><a href="${baseUri}/applied/ex20/" class="list-group-item">単票入力系2: 登録入力 - 補助入力 - 補助確認 - 登録入力 - 登録確認 - 変更入力(登録完了メッセージ) - 補助入力 -
					補助確認 - 変更入力 - 変更確認 - 変更入力(変更完了メッセージ)</a></li>
			<li><a href="${baseUri}/applied/ex30/" class="list-group-item">検索一覧系1: 検索条件入力 - 検索結果一覧 - 単票変更入力 - 単票補助入力 - 単票補助確認 - 単票変更入力 - 単票変更確認
					- 単票変更完了 - 検索結果一覧または検索条件入力</a></li>
			<li><a href="${baseUri}/applied/ex40/" class="list-group-item">検索一覧系2: 検索条件入力 - 検索結果一覧 - 単票変更入力 - 単票補助入力 - 単票補助確認 - 単票変更入力 - 単票変更確認
					- 単票変更入力(変更完了メッセージ) - 検索結果一覧または検索条件入力</a></li>
			<li><a href="${baseUri}/applied/ex50/" class="list-group-item">一括変更系1: 検索条件入力 - 検索結果一覧(対象選択) - 一括変更入力 - 補助入力 - 補助確認 - 一括変更入力(補足反映) -
					一括変更確認 - 一括変更完了 - 検索結果一覧または検索条件入力</a></li>
			<li><a href="${baseUri}/applied/ex60/" class="list-group-item">一括変更系2: 検索条件入力 - 検索結果一覧(対象選択) - 一括変更入力 - 補助入力 - 補助確認 - 一括変更入力(補足反映) -
					一括変更確認 - 一括変更入力(変更完了メッセージ) - 検索結果一覧または検索条件入力</a></li>
			<li><a href="${baseUri}/applied/ex90/" class="list-group-item">アップロード系1: 入力 - 確認 - 完了</a></li>
		</ul>
	</div>
	<div id="form" class="tab-pane" role="tabpanel">
		<ul class="list-group list-unstyled">
			<li><a href="${baseUri}/form/ex10/" class="list-group-item">単票サブフォーム</a></li>
			<li><a href="${baseUri}/form/ex20/" class="list-group-item">一覧サブフォーム</a></li>
			<li><a href="${baseUri}/form/ex30/" class="list-group-item">多段サブフォーム(リストのリスト)</a></li>
			<li><a href="${baseUri}/form/ex40/" class="list-group-item">多段サブフォーム(マップのマップ)</a></li>
			<li><a href="${baseUri}/form/ex50/" class="list-group-item">行列型チェックボックス(多段行・多段列)</a></li>
		</ul>
	</div>
	<div id="validation" class="tab-pane" role="tabpanel">
		<ul class="list-group list-unstyled">
			<li><a href="${baseUri}/validation/ex10/" class="list-group-item">文字列項目</a></li>
			<li><a href="${baseUri}/validation/ex20/" class="list-group-item">数値項目</a></li>
			<li><a href="${baseUri}/validation/ex30/" class="list-group-item">日時項目</a></li>
			<li><a href="${baseUri}/validation/ex40/" class="list-group-item">サブフォーム</a></li>
			<li><a href="${baseUri}/validation/ex50/" class="list-group-item">グルーピング</a></li>
		</ul>
	</div>
</div>
