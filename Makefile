MVN = mvn

dev-platform:
	cd qtos-platform && $(MVN) spring-boot:test-run -Dspring-boot.run.profiles=dev

build-platform:
	cd qtos-platform && $(MVN) package

build-base:
	cd qtos-base && $(MVN) package

run-platform:
	cd qtos-platform && $(MVN) spring-boot:run

run-base:
	cd qtos-base && $(MVN) spring-boot:run

clean:
	rm -rf qtos-platform/target/
	rm -rf qtos-base/target/
