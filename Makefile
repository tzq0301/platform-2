MVN = mvn

build:
	$(MVN) -f qtos-pom package

dev-platform:
	$(MVN) -f qtos-platform spring-boot:test-run -Dspring-boot.run.profiles=dev

run-platform:
	$(MVN) -f qtos-platform spring-boot:run

run-base:
	$(MVN) -f qtos-base spring-boot:run

clean:
	rm -rf qtos-platform/target/
	rm -rf qtos-base/target/
	rm -rf qtos-base/localdeploydir/
