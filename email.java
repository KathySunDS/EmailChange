import java.util.*;

public class Change {
    public static Map<String, List<String>> getAllEmails(Map<String, String> emailChanges) {
        Map<String, List<String>> userEmails = new HashMap<>();
        Set<String> visited = new HashSet<String>();

        for (Map.Entry<String, String> entry : emailChanges.entrySet()) {
            String currentEmail = entry.getKey();
            if (visited.contains(currentEmail)) {
                continue;
            }
            if (!visited.contains(currentEmail)){
                visited.add(currentEmail);
            }
            List<String> emails = userEmails.getOrDefault(currentEmail, new ArrayList<>());

            String priorEmail = entry.getValue();
            if (priorEmail != null) {
                // traverse prior email recursively
                emails.addAll(traversePriorEmails(priorEmail, emailChanges));
            }

            emails.add(currentEmail);
            userEmails.put(currentEmail, emails);
        }

        return userEmails;
    }

    private static List<String> traversePriorEmails(String priorEmail, Map<String, String> emailChanges) {
        String email = emailChanges.get(priorEmail);
        List<String> emails = new ArrayList<>();

        if (email != null) {
            emails.addAll(traversePriorEmails(email, emailChanges));
        }

        emails.add(priorEmail);
        return emails;
    }

    public static void main(String[] args) {
        Map<String, String> emailChanges = new HashMap<>();
        emailChanges.put("a@example.com", "b@example.com");
        emailChanges.put("b@example.com", "c@example.com");
        emailChanges.put("x@example.com", "y@example.com");
        emailChanges.put("y@example.com", "z@example.com");
        // Add more entries if needed

        Map<String, List<String>> allEmails = getAllEmails(emailChanges);
        
        System.out.println(allEmails);
    }
}
