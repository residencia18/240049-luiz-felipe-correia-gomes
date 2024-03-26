#include <iostream>

int main (void)
{

    for (int i = 1; i < 101; i++)
    {
        std:: cout << i << " ";
        if (i % 3 == 0) 
        {
            // !(i % 3)
            std:: cout << "Fizz";
        }

        if (i % 5 == 0) 
        {
            std:: cout << "Buzz"; 
        }

        std:: cout << std:: endl;
    }
    
}