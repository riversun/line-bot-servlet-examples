# line-bot-servlet-examples

**English Below**


## Eclipseへのインポートと実行

### Eclipseへのimport

1.Select File>Import>Git - Projects from Git  
2.Clone URI  
3.set clone URI to https://github.com/riversun/line-bot-servlet-examples.git  
4.適宜[NEXT]を押していく
5."Import as general project"をチェックして、"finish"  を押す


### creentialsをセットする

1.プロジェクト上で、右クリック
2.Configure>Convert to Maven project  
3.(これでmaven projectとしてEclipseに読み込まれます)  
4.secret,access_tokenをセット

src/main/java直下にcredentials.propertiesというファイルを作り、以下のように取得したchannel_secret,channel_access_tokenをセットします

```
line.channel_secret=xxxxxxxxxxxxxxxxxxxxxxxxxxx
line.channel_access_token=xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
```

<hr>
### Run

1.Run AppMain.java  で実行



## How to import into your Eclipse and Run.

### Import into Eclipse

1.Select File>Import>Git - Projects from Git  
2.Clone URI  
3.set clone URI to https://github.com/riversun/line-bot-servlet-examples.git  
4.Select next along the flow  
5.Check "Import as general project" and select "finish"  


### After import

1.Right click on Project  
2.Configure>Convert to Maven project  
3.(Now you can handle this project as a maven project)  
4.Set secret,access_token

Create file "credentials.properties" under "src/main/java" and write channel_secret and channel_access_token in it like below.

```
line.channel_secret=xxxxxxxxxxxxxxxxxxxxxxxxxxx
line.channel_access_token=xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
```

### Run

1.Run AppMain.java  



