#include <gtest/gtest.h>

extern "C" {
#include "countfeet.h"
}

// This class definition is required by Google Test.
// See the documentation for further details.
class ChallengeTests : public ::testing::Test {
 protected:
  // Constructor runs before each test
  ChallengeTests() {}
  // Destructor cleans up after tests
  virtual ~ChallengeTests() {}
  // Sets up before each test (after constructor)
  virtual void SetUp() {}
  // Clean up after each test (before destructor)
  virtual void TearDown() {}
};

TEST(ChallengeTests, causality_holds) {
  EXPECT_TRUE(1) << "Kudos if you can actually trigger this message\n";
  EXPECT_FALSE(0) << "Kudos if you can actually trigger this message\n";
}


int main(int argc, char **argv) {
  testing::InitGoogleTest(&argc, argv);
  return RUN_ALL_TESTS();
}
