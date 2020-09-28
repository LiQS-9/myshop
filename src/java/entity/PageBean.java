package entity;

import lombok.Data;
import utils.Constants;

import java.util.List;

@Data
public class PageBean {
    private long count; // 一共有几条
    private List list; // 当前页数据
    private int curPage; // 当前是第几页
    private long pages; // 总页数
    private long pre; // 上一页
    private long next; // 下一页
    private long first = 1; // 首页
    private long last; // 尾页
    private boolean hasPre; // 是否有上一页
    private boolean hasNext; // 是否有下一页
    public PageBean(){}

    public PageBean(long count, List list, int curPage) {
        this.count = count;
        this.list = list;
        this.curPage = curPage;
        pages = count % Constants.PAGE_SIZE == 0 ? count / Constants.PAGE_SIZE : count / Constants.PAGE_SIZE + 1;
        hasPre = curPage - 1 > 0;
        hasNext = curPage + 1 <= pages;
        pre = hasPre ? curPage - 1 : 1;
        next = hasNext ? curPage + 1 : pages;
        last = pages;
    }
}
