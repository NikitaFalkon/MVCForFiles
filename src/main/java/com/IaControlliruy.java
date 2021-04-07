package com;

import net.sf.jmimemagic.Magic;
import net.sf.jmimemagic.MagicException;
import net.sf.jmimemagic.MagicMatchNotFoundException;
import net.sf.jmimemagic.MagicParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.RedirectView;

import java.io.File;
import java.io.IOException;

@Controller
public class IaControlliruy {
    @Autowired
    RestTemplate restTemplate;
    @GetMapping("/")
    public String FindFile(Model model)
    {
        String filename ="";
        model.addAttribute("filename", filename);
        return "Find";
    }
    @GetMapping(value = "/finding")
    public RedirectView Fil(@RequestParam(name = "filename") String filename) throws IOException, MagicParseException, MagicException, MagicMatchNotFoundException {
        File file = new File("c:\\FilesForJava\\"+filename);
        String mimeType = Magic.getMagicMatch(file, false).getMimeType();
        switch (mimeType) {
            case ("application/pdf") :
                return new RedirectView("http://localhost:8060/pdf/"+filename);
            case ("image/jpeg"):
                return new RedirectView("http://localhost:8060/jpeg/"+filename);
            case ("text/plain"):
                return new RedirectView("http://localhost:8060/txt/"+filename);
            case ("application/json"):
                return new RedirectView("http://localhost:8060/json/"+filename);
            case ("text/xml"):
                return new RedirectView("http://localhost:8060/xml/"+filename);
            default:
                return null;
        }
    }
}
