#include "bits.h"
#include "cache.h"

int get_set(Cache *cache, address_type address) {
  // TODO:
  //  Extract the set bits from a 32-bit address.
  //
  int b = cache->block_bits;
  int s = cache->set_bits;
  int t = 32 - b - s;
  unsigned int bit = (((unsigned)(address << t) >> t) >> b);
  return bit;
}

int get_line(Cache *cache, address_type address) {
  // TODO:
  // Extract the tag bits from a 32-bit address.
  //
  int b = cache->block_bits;
  int s = cache->set_bits;
  int c = b + s;
  unsigned int bit = ((unsigned)address >> c);
  return bit;
}

int get_byte(Cache *cache, address_type address) {
  // TODO
  // Extract the block offset (byte index) bits from a 32-bit address.
  //
  int b = cache->block_bits;
  int c = 32 - b;
  unsigned int bit = ((unsigned)(address << c) >> c);
  return bit;
}
