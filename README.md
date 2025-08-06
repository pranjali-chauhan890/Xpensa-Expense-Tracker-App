# 💸 Xpensa – Expense Tracker App

Xpensa is a simple and efficient Android app designed to help users track their daily expenses. With a clean UI and intuitive functionality, Xpensa makes managing finances easy and accessible.

⭐ If you found this project helpful, consider giving it a *star* to show support!

---

## ✨ Features

- ➕ Add income and expense entries with description and amount  
- 📊 Track your balance, income, and expenses in real-time  
- 🗑 Delete entries with a single tap  
- 🧮 Automatic total calculations  
- 🧠 Stores data locally using Room database  
- 🎨 Clean and modern Material Design interface

---

## 🛠 Built With

- *Java*  
- *XML*  
- *Room Database*  
- *MVVM Architecture*  
- *RecyclerView*  
- *LiveData & ViewModel*  
- *Android Studio*  
- *Material Components*

---

## 🔧 Logic Used

- Room handles local data persistence (@Entity, @Dao, Database)  
- MVVM separates UI, data, and logic for better code structure  
- RecyclerView displays all transactions dynamically  
- Observers automatically update totals when data changes  
- Amounts are filtered as positive (income) or negative (expense)  
- Data validation ensures empty fields are not saved
