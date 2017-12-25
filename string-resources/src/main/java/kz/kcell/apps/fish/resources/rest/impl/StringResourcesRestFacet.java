package kz.kcell.apps.fish.resources.rest.impl;

import kz.kcell.apps.fish.domain.spmot.entity.SpmotResourceBundle;
import kz.kcell.apps.fish.resources.rest.api.StringResourcesRestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.util.stream.Stream;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 14 09 2015
 */
@Service
@Slf4j
@RestController()
@RequestMapping("/resources")
public class StringResourcesRestFacet implements StringResourcesRestService {

//    @Autowired
//    private StringResourcesService resourcesService;

    class Query{
        public String str;
    }

    //    @Override
    @GET
    @Path("/")
    @RequestMapping("")
    public String getlist(@QueryParam("q") @RequestParam(name ="q", defaultValue = ".*") String queryString) {
        StringBuilder buf = new StringBuilder();

        final Query query = new Query();

        if(StringUtils.isEmpty(queryString)) {
            query.str = ".*";
        } else {
            query.str = queryString;
        }
        
        buf.append("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "<style>\n" +
                "table {\n" +
                "    font-size:16px;\n" +
                "    font-family: \"Trebuchet MS\", Arial, Helvetica, sans-serif;\n" +
                "    border-collapse: collapse;\n" +
                "    border-spacing: 0;\n" +
                "    width: 100%;\n" +
                "}\n" +
                "\n" +
                " td,  th {\n" +
                "    border: 1px solid #ddd;\n" +
                "    text-align: left;\n" +
                "    padding: 8px;\n" +
                "}\n" +
                "\n" +
                " tr:nth-child(even){background-color: #f2f2f2}\n" +
                "\n" +
                " th {\n" +
                "    padding-top: 11px;\n" +
                "    padding-bottom: 11px;\n" +
                "    background-color: #4CAF50;\n" +
                "    color: white;\n" +
                "}\n" +
                "</style>\n" +
                "</head>\n" +
                "<body>" +
                "Qeury in regexp format: <a href='?q=ussd_.*'>?q=ussd_.*</a>");
        
        buf.append("<table style=''><tr><th>name</th><th>ru</th><th>kk</th><th>en</th></tr>");
        Stream.of(SpmotResourceBundle.values())
                .filter(r -> r.name().matches(query.str))
                .sorted((r1, r2) -> r1.name().compareTo(r2.name()))
                .forEachOrdered(r -> {
            buf.append(
                    String.format("<tr><td>%s</td><td>%s</td><td>%s</td><td>%s</td></tr>",
                            r.name(),r._ru(),r._kk(),r._en()));

        });
        buf.append("</table>");

/*
        StringBuilder buf = new StringBuilder();
        Stream.of(SpmotResourceBundle.values()).forEach(r ->{
            buf.append(r.name());
            buf.append("\t"+r._ru());
            buf.append("\t"+r._kk());
            buf.append("\t"+r._en());

            buf.append("\n");
        });
*/


        return buf.toString();
    }
}
