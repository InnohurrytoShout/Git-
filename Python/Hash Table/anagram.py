# Do not change the name of this file or the class name.

class Anagram(object):

    def __init__(self, filename):
        self.load_dictionary(filename)

    """
    Helper method to load the dictionary file.
    You may read it in some other way if you want to but do not change the function signature.
    """
    def load_dictionary(self, filename):
        with open(filename) as handle:
            self.dictionary = set(w.strip() for w in handle)
    """   
   * Implement the algorithm here. Do not change the function signature.
   *
   * @returns - List of lists, where each element of the outer list is a list containing all the words in that anagram class.
   * example: [['pots', 'stop', 'tops'], ['brake', 'break']]
    """

    def getAnagrams(self):
        diclist = list(self.dictionary) #change the set of dictionary to list
        size = len(diclist) #get the length of dictionary
        hash = hstable(size) #initiate a self implemented hashtable
        finlist = [] #define the final returned list
        initlist = [0] * 26 #Create a length 26 list to count every character a word have
        i = 0

        for words in diclist:   #iterate through all the words in the list
            for char in words:  #iterate through all the character in the word
                initlist[(ord(char) - 97)] += 1 #get the count of every character
            hash.add(initlist, words, -1) #add item into the hash table
            initlist = [0] * 26 #clean up the list

        for element in hash.hashtable:  #get item from the hash table to put into the final list
            if element[0] != None:  #To get rid of all the element that does not have a key or word or anything
                finlist.append(element[1]) #add the whole anagram class into the final list
        return finlist


class hstable(): #self implemented hash table
    def __init__(self, size): #initaiating of the hash table
        self.capacity = 4 * size    #give the hash table enough space to reduce possible collision
        self.hashtable = [[None, []] for _ in range(self.capacity)] #the actual hash table

    def hashfunction(self, key): #the hash function, change the passed in character counting list as key to the hash value we need
        i = 0
        hashkey = 0
        count = 0 #count how many character a word have
        countercount = 0 #count how many zero the key list have
        for j in key:
            if j != 0:
                count += 1
            else:
                countercount +=1

        while i < 26:
            hashkey += ((key[i] + 97) * i + count * count) * i * i + countercount * i - count #to increase the random property of each hash value to decrease the possibility of collision
            i += 1
        return hashkey % self.capacity  #same purpose

    def add(self, key, value, hash_v):  #method of adding stuff into the hashtable
        if hash_v == -1:    #a judge for whether the function is called recursively or normal call.
            hash_value = self.hashfunction(key)
        else:
            hash_value = (hash_v + 3)*2 % self.capacity #if recursive call then change the hash key value
        if self.hashtable[hash_value][0] == None:  # key is none
            self.hashtable[hash_value][0] = key
            self.hashtable[hash_value][1].append(value)
        elif self.hashtable[hash_value][0] == key:  # key is not none, but same
            self.hashtable[hash_value][1].append(value)
        else:  # key is not none, not same,collision, thus recursively call the add method
            self.add(key, value, hash_value)


"""
You can use this for debugging if you wish.
"""
if __name__ == "__main__":
    pf = Anagram("dict2.txt")
    pf.getAnagrams()

