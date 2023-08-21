class Change:
    @staticmethod
    def get_all_emails(email_changes):
        user_emails = {}
        visited = set()

        for current_email, prior_email in email_changes.items():
            if current_email in visited:
                continue
            
            visited.add(current_email)
            emails = user_emails.get(current_email, [])
            
            if prior_email is not None:
                # traverse prior email recursively
                emails.extend(Change.traverse_prior_emails(prior_email, email_changes, visited))
            
            emails.append(current_email)
            user_emails[current_email] = emails
        
        return user_emails

    @staticmethod
    def traverse_prior_emails(prior_email, email_changes, visited):
        if prior_email in visited:
            return
            
        visited.add(prior_email)

        email = email_changes.get(prior_email)
        emails = []

        if email is not None:
            emails.extend(Change.traverse_prior_emails(email, email_changes, visited))
        
        emails.append(prior_email)
        return emails

if __name__ == "__main__":
    email_changes = {
        "a@example.com": "b@example.com",
        "b@example.com": "c@example.com",
        "x@example.com": "y@example.com",
        "y@example.com": "z@example.com",
        # Add more entries if needed
    }

    all_emails = Change.get_all_emails(email_changes)

    print(all_emails)
