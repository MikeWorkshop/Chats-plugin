Come utilizzare questo plugin:

Comando principale: /chat nomechat messaggio; invia il messaggio inserito a tutti i giocatori con il permesso dedicato alla chat inserita

Comando admin: /achat (crea | elimina | setprefix) nomechat; comando riseravato allo staff per la gestione delle chat

File lista_chats.yml contiene tutte le chat e i dati relativi, utilizzando chatgpt si possono generare tutte le chat necessarie per il proprio server in pochi minuti, basta fargli seguire il formato utilizzato dal plugin (si può generare una chat di prova in game per avere il formato). caricando la lista generata da chatgpt e riavviando il server si potranno visualizzare e utilizzare le nuove chat.

Per usare l'API:
1- Add to pom.xml

	<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
2- Add the dependency

	<dependency>
	    <groupId>com.github.MikeWorkshop</groupId>
	    <artifactId>Chats-plugin</artifactId>
	    <version>1.0</version>
		<scope>provided</scope>
	</dependency>
