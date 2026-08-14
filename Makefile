
SOURCES := $(shell find * -name "*.java")
BIN_DIR=out
MAIN_CLASS := es.fortytwo.avaj.simulator.Simulator

run: all
	java -cp $(BIN_DIR) $(MAIN_CLASS) scenario.txt
	cat simulation.txt

all:
	mkdir -p $(BIN_DIR)
	javac -d $(BIN_DIR) $(SOURCES)

clean:
	find * -name "*.class" -delete
	rm -rf simulation.txt
	rm -rf sources.txt
	rm -rf $(BIN_DIR)

subject:
	find * -name "*.java" > sources.txt
	javac @sources.txt
	java -cp src es.fortytwo.avaj.simulator.Simulator scenario.txt
	cat simulation.txt

.PHONY: all clean subject
