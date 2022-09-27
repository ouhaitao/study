package 回溯;

import java.util.HashSet;

/**
 * @author parry
 * @date 2021/07/09
 */
public class 字典树and回溯 {
    
    public static void main(String[] args) {
        字典树and回溯 main = new 字典树and回溯();
        MagicDictionary magicDictionary = main.new MagicDictionary();
        magicDictionary.buildDict(new String[]{"hello", "leetcode"});
        magicDictionary.search("hello"); // 返回 False
        magicDictionary.search("hhllo"); // 将第二个 'h' 替换为 'e' 可以匹配 "hello" ，所以返回 True
        magicDictionary.search("hell"); // 返回 False
        magicDictionary.search("leetcoded"); // 返回 False
    }
    
    class MagicDictionary {
        
        TrieTree root;
        
        public MagicDictionary() {
            root = new TrieTree();
        }
        
        public void buildDict(String[] dictionary) {
            for (String s : dictionary) {
                TrieTree cur = root;
                for (int i = 0; i < s.length(); i++) {
                    int index = s.charAt(i) - 'a';
                    if (cur.child[index] == null) {
                        cur.child[index] = new TrieTree();
                    }
                    cur = cur.child[index];
                }
                cur.isFinish = true;
            }
        }
        
        public boolean search(String searchWord) {
            return dfs(searchWord, 0, root, false);
        }
        
        public boolean dfs(String word, int wordIndex, TrieTree curNode, boolean modified) {
            if (wordIndex == word.length()) {
                return modified && curNode.isFinish;
            }
            int index = word.charAt(wordIndex) - 'a';
            if (curNode.child[index] != null) {
                if (dfs(word, wordIndex + 1, curNode.child[index], modified)) {
                    return true;
                }
            }
            if (!modified) {
                for (int i = 0; i < 26; i++) {
                    if (i != index && curNode.child[i] != null) {
                        if (dfs(word, wordIndex + 1, curNode.child[i], true)) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }
    }
    
    class TrieTree {
        TrieTree[] child;
        boolean isFinish;
    
        public TrieTree() {
            child = new TrieTree[26];
            isFinish = false;
        }
    }
}
