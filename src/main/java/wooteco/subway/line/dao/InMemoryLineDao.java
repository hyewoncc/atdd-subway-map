package wooteco.subway.line.dao;

import org.springframework.util.ReflectionUtils;
import wooteco.subway.line.Line;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class InMemoryLineDao implements LineDao {

    private final List<Line> lines = new ArrayList<>();
    private Long seq = 0L;

    @Override
    public boolean existsByName(String name) {
        return lines.stream()
                .anyMatch(line -> line.isSameName(name));
    }

    @Override
    public Line save(Line line) {
        Line persistLine = createNewObject(line);
        lines.add(persistLine);
        return persistLine;
    }

    private Line createNewObject(Line line) {
        Field field = ReflectionUtils.findField(Line.class, "id");
        field.setAccessible(true);
        ReflectionUtils.setField(field, line, ++seq);
        return line;
    }

    @Override
    public List<Line> findAll() {
        return lines;
    }

    @Override
    public boolean existsById(Long id) {
        return lines.stream()
                .anyMatch(line -> line.isSameId(id));
    }

    @Override
    public Line findById(Long id) {
        return lines.stream()
                .filter(line -> line.isSameId(id))
                .findAny()
                .get();
    }

    @Override
    public void removeById(Long id) {
        lines.removeIf(line -> line.isSameId(id));
    }

    @Override
    public void update(Long id, Line line) {
        findById(id).changeInfo(line.getName(), line.getColor());
    }
}