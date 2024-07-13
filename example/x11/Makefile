.PHONY: package clean

package:
	mkdir -p packages
	cd display && tar -zc --no-xattrs --no-mac-metadata -f display.tar.gz display.sh install.sh uninstall.sh
	cd ui && tar -zc --no-xattrs --no-mac-metadata -f ui.tar.gz simcity-ui.jar install.sh uninstall.sh
	cp {display/display.tar.gz,ui/ui.tar.gz} packages

test: package
	bash test.sh

clean:
	rm -rf packages/