MVN = mvn

build-platform:
	cd qtos-platform && $(MVN) package

run-platform:
	cd qtos-platform && $(MVN) spring-boot:run

clean:
	rm -rf qtos-platform/target/
