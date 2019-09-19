package com.bbs.domain;

/**
 *   `wordId` int(11) NOT NULL AUTO_INCREMENT,
 *   `word` varchar(255) DEFAULT NULL COMMENT '敏感词',
 *   `status` int(2) DEFAULT '0' COMMENT '是否启用',
 */
public class Word {

    private Integer wordId;
    private Integer status;//是否启用
    private String word;//敏感词

    public Integer getWordId() {
        return wordId;
    }

    public void setWordId(Integer wordId) {
        this.wordId = wordId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    @Override
    public String toString() {
        return "Word{" +
                "wordId=" + wordId +
                ", status=" + status +
                ", word='" + word + '\'' +
                '}';
    }
}
