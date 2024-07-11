.PHONY: compile build-jar build package clean

all: package

compile:
	javac client/Client.java
	javac server/Server.java

build-jar: compile
	cd client && jar cfe client.jar Client Client.class
	cd server && jar cfe server.jar Server Server.class

build: build-jar

package: build
	mkdir -p packages
	cd client && tar -zc --no-xattrs --no-mac-metadata -f client.tar.gz client.jar install.sh uninstall.sh
	cd server && tar -zc --no-xattrs --no-mac-metadata -f server.tar.gz server.jar install.sh uninstall.sh
	cp {client/client.tar.gz,server/server.tar.gz} packages/

test: package
	bash test.sh

clean:
	rm -rf client/*.{class,jar,tar.gz}
	rm -rf server/*.{class,jar,tar.gz}
	rm -rf packages/
