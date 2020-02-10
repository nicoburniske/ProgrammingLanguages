package answer;

import org.json.simple.JSONArray;

public class AnswerCell extends Answer<Cell> {
    public AnswerCell(Cell result) {
        super(result);
    }

    @Override
    public String toJSONString() {
        return result.toJSONString();
    }
}
