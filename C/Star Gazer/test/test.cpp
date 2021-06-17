#include <gtest/gtest.h>
#include <stdlib.h>
#include <time.h>

// needed to access the C code
extern "C" {
#include "star.h"
}

// This class definition is required by Google Test.
// See the documentation for further details.
class star_test : public ::testing::Test {
 protected:
  // Constructor runs before each test
  star_test() {}
  // Destructor cleans up after tests
  virtual ~star_test() {}
  // Sets up before each test (after constructor)
  virtual void SetUp() {}
  // Clean up after each test (before destructor)
  virtual void TearDown() {}
};


TEST(star_test, causality_holds) {
  EXPECT_TRUE(1) << "Kudos if you can actually trigger this message";
  EXPECT_FALSE(0) << "Kudos if you can actually trigger this message";
}


int main(int argc, char** argv) {
  testing::InitGoogleTest(&argc, argv);
  return RUN_ALL_TESTS();
}
