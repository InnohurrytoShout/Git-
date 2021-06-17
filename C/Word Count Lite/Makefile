_DEPS = main.h
_MOBJ = main.o
_TOBJ = test.o

APPBIN  = wc_lite_app
TESTBIN = wc_lite_test

IDIR = include
CC = gcc
CFLAGS = -I$(IDIR) -Wall -Wextra -g -pthread
ODIR = obj
SDIR = src
LDIR = lib
TDIR = test
LIBS = -lm
XXLIBS = $(LIBS) -lstdc++ -lgtest -lgtest_main -lpthread
DEPS = $(patsubst %,$(IDIR)/%,$(_DEPS))
MOBJ = $(patsubst %,$(ODIR)/%,$(_MOBJ))
TOBJ = $(patsubst %,$(ODIR)/%,$(_TOBJ))
SRC = $(wildcard $(SDIR)/*.c)

$(ODIR)/%.o: $(SDIR)/%.c $(DEPS)
	$(CC) -c -o $@ $< $(CFLAGS)

$(ODIR)/%.o: $(TDIR)/%.cpp $(DEPS)
	$(CC) -c -o $@ $< $(CFLAGS)

all: $(APPBIN) $(TESTBIN) submission-wc-lite.zip

# Shorter names for convenience and more granular targeting
app: $(APPBIN)

test: $(TESTBIN)

sub: submission-wc-lite.zip

$(APPBIN): $(MOBJ)
	$(CC) -o $@ $^ $(CFLAGS) $(LIBS)

$(TESTBIN): $(TOBJ)
	$(CC) -o $@ $^ $(CFLAGS) $(XXLIBS)

submission-wc-lite.zip: $(SRC) $(DEPS)
	zip submission-wc-lite.zip $(SRC) $(DEPS)


.PHONY: clean

clean:
	rm -f $(ODIR)/*.o *~ core $(IDIR)/*~ $(SDIR)/*~
	rm -f $(APPBIN) $(TESTBIN)
	rm -f ex/out.txt
	rm -f submission-wc-lite.zip
