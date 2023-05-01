# Chatapp

## 機能
* ユーザーの登録、認証機能
* テキストチャンネルの選択、登録
* メッセージの送信、削除機能  
(削除はテキストチャンネルの作成者及びメッセージの送信者のみ可能)

## 技術

* Java, Spring Boot  
バックエンドの実装に使用  

* Typescript, React  
フロントエンドの実装に使用

* Nginx, Tomcat  
バックエンド、フロントエンドのサーブに使用

* Docker, Docker Compose  
実行環境、開発環境の構築の自動化に使用

## 実行方法
事前に [Docker Compose](https://docs.docker.com/compose/install/) をインストールする必要があります。  

1. レポジトリのクローン
```
$ git clone https://github.com/kirino3202/chatapp && cd chatapp
```

2. イメージのビルド (10分ほどかかります)
```
$ sudo docker-compose -f docker-compose-prod.yml build
```

3. コンテナの起動
```
$ sudo docker-compose -f docker-compose-prod.yml up
```

4. [localhost](http://localhost/)にアクセスするとログイン画面が表示されます
